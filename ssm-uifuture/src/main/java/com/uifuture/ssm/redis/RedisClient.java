package com.uifuture.ssm.redis;

import com.google.common.collect.Lists;
import com.uifuture.ssm.exception.CacheException;
import com.uifuture.ssm.redis.config.ConnConfig;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.scripting.ScriptSource;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class RedisClient extends AbstractClient implements InitializingBean, DisposableBean {

    private final int minIdle = 100;
    private final int maxIdle = 200;
    @Setter
    @Getter
    private ConnConfig connConfig;
    private JedisConnectionFactory factory;
    @Getter
    private RedisTemplate<String, String> redisTemplate;
    @Getter
    private RedisMessageListenerContainer messageContainer = new RedisMessageListenerContainer();

    /**
     * 删除值
     *
     * @param key
     */
    @Override
    public void delete(String key) {
        if (StringUtils.isEmpty(key)) {
            return;
        }
        String realKey = getRealKey(connConfig.getApp(), key);
        redisTemplate.delete(realKey);
    }


    /**
     * 设置值，过期时间单位秒
     *
     * @param key
     * @param value
     * @param expire
     */
    @Override
    protected void doSet(String key, String value, int expire) {
        String realKey = getRealKey(connConfig.getApp(), key);
        redisTemplate.opsForValue().set(realKey, value, expire, TimeUnit.SECONDS);
    }


    @Override
    protected String doGet(String key) {
        String realKey = getRealKey(connConfig.getApp(), key);
        return redisTemplate.opsForValue().get(realKey);
    }


    public long setWithExpireIfAbsent(String key, String value, long expire) {
        try {
            ScriptSource scriptSource = new ResourceScriptSource(new ClassPathResource("script/setIfAbsentGetExpire.lua"));
            DefaultRedisScript<Long> s = new DefaultRedisScript<>(scriptSource.getScriptAsString(), Long.class);
            String realKey = getRealKey(connConfig.getApp(), key);
            List<String> keys = Lists.newArrayList(realKey);
            return redisTemplate.execute(s, keys, value, expire + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -3;
    }

    public boolean setnxAndExpire(String key, String value, long expire) {
        try {
            ScriptSource scriptSource = new ResourceScriptSource(new ClassPathResource("script/setnxAndExpire.lua"));
            DefaultRedisScript<Boolean> s = new DefaultRedisScript<>(scriptSource.getScriptAsString(), Boolean.class);
            String realKey = getRealKey(connConfig.getApp(), key);
            List<String> keys = Lists.newArrayList(realKey);
            return redisTemplate.execute(s, keys, value, expire + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }


    public boolean compareAndDelete(String key, String value) {
        try {
            ScriptSource scriptSource = new ResourceScriptSource(new ClassPathResource("script/compareAndDelete.lua"));
            DefaultRedisScript<Boolean> s = new DefaultRedisScript<>(scriptSource.getScriptAsString(), Boolean.class);
            String realKey = getRealKey(connConfig.getApp(), key);
            List<String> keys = Lists.newArrayList(realKey);
            return redisTemplate.execute(s, keys, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public void destroy() throws Exception {
        if (messageContainer != null) {
            messageContainer.destroy();
        }
        if (factory != null) {
            factory.destroy();

        }
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        if (connConfig == null) {
            throw new CacheException(500, "init ConnConfig first");
        }
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMinIdle(minIdle);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        factory = new JedisConnectionFactory();
        factory.setPoolConfig(poolConfig);
        factory.setHostName(connConfig.getHost());
        factory.setPort(connConfig.getPort());
        factory.setPassword(connConfig.getPassword());
        factory.setUsePool(true);
        factory.afterPropertiesSet();
        redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        RedisSerializer<String> serializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(serializer);
        redisTemplate.setValueSerializer(serializer);
        redisTemplate.afterPropertiesSet();
        messageContainer.setConnectionFactory(factory);
        messageContainer.afterPropertiesSet();
        messageContainer.start();
    }

    /**
     * 以原子方式将当前值增加1
     *
     * @param key
     * @param liveTime 只有第一次才会设置过期时间
     * @return 更新前的值，也就是+1前的值
     */
    public long incr(String key, long liveTime) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        long increment = entityIdCounter.getAndIncrement();
        //初始设置过期时间,这是由于RedisAtomicLong(String redisCounter, RedisConnectionFactory factory, @Nullable Long initialValue)方法中set方法没有设置过期时间
        if (increment == 0 && liveTime > 0) {
            entityIdCounter.expire(liveTime, TimeUnit.SECONDS);
        }
        return increment;
    }

    /**
     * 以原子方式将当前值增加1
     *
     * @param key
     * @param liveTime 只有第一次才会设置过期时间
     * @return 更新前的值，也就是+1前的值
     */
    public int incrInt(String key, long liveTime) {
        RedisAtomicInteger entityIdCounter = new RedisAtomicInteger(key, redisTemplate.getConnectionFactory());
        int increment = entityIdCounter.getAndIncrement();
        //初始设置过期时间,这是由于RedisAtomicLong(String redisCounter, RedisConnectionFactory factory, @Nullable Long initialValue)方法中set方法没有设置过期时间
        if (increment == 0 && liveTime > 0) {
            entityIdCounter.expire(liveTime, TimeUnit.SECONDS);
        }
        return increment;
    }

}
