package com.uifuture.ssm.redis;

import com.uifuture.ssm.redis.listener.AppRedisMessagePublisher;
import com.uifuture.ssm.redis.listener.AppRedisMessageSubcriber;
import com.uifuture.ssm.redis.listener.MessageData;
import lombok.Data;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.util.StringUtils;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class RedisLock implements InitializingBean {

    private static ConcurrentHashMap<String, Integer> mutexMap = new ConcurrentHashMap<>();
    private static ThreadLocal<List<IndentifyValue>> threadLocalValues = new ThreadLocal<>();
    private static String ip;
    private static String prefix = "lightningx:lock:";
    private static AtomicLong inc = new AtomicLong();
    private static String channel = "lightningx-cache-lock";
    @Setter
    private RedisClient client;
    private AppRedisMessagePublisher publisher;

    /**
     * 加锁 可重入锁
     *
     * @param key
     * @param timeout 等待时间
     * @throws InterruptedException
     */
    public void lock(String key, int timeout) throws InterruptedException {
        key = prefix + key;
        Integer m = new Integer(0);
        Integer mutex = mutexMap.putIfAbsent(key, m);
        if (mutex == null) {
            mutex = m;
        }
        String value = ip + "." + inc.getAndIncrement();
        long existsTimeOut = 0;
        synchronized (mutex) {
            while ((existsTimeOut = client.setWithExpireIfAbsent(key, value, timeout)) != -2) {
                mutex.wait(existsTimeOut);
            }
        }
        setThreadLocalValue(key, value);
    }

    /**
     * 加锁 非可重入锁，防止并发
     *
     * @param key
     * @param timeout 加锁时长
     * @return
     */
    public boolean tryLock(String key, int timeout) {
        if (StringUtils.isEmpty(key)) {
            return false;
        }
        key = prefix + key;
        String value = ip + "." + inc.getAndIncrement();
        if (client.setnxAndExpire(key, value, timeout)) {
            setThreadLocalValue(key, value);
            return true;
        }
        return false;
    }

    /**
     * 释放锁
     *
     * @param key
     */
    public void unlock(String key) {
        key = prefix + key;
        List<IndentifyValue> values = threadLocalValues.get();
        if (values == null) {
            return;
        }
        List<IndentifyValue> tvs = new ArrayList<>();
        String value = null;
        for (IndentifyValue i : values) {
            if (!i.getKey().equals(key)) {
                tvs.add(i);
            } else {
                value = i.getValue();
            }
        }
        threadLocalValues.set(tvs);
        if (value == null) {
            return;
        }
        if (client.compareAndDelete(key, value)) {
            publisher.sendKeyMessage(channel, key);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ip = InetAddress.getLocalHost().getHostAddress();
        ChannelTopic topic = new ChannelTopic(channel);
        client.getMessageContainer().addMessageListener(new KeyMessageListener(client), topic);
        publisher = new AppRedisMessagePublisher(client);
    }


    private void setThreadLocalValue(String key, String value) {
        List<IndentifyValue> values = threadLocalValues.get();
        if (values == null) {
            values = new ArrayList<>();
        }
        IndentifyValue iv = new IndentifyValue();
        iv.setKey(key);
        iv.setValue(value);
        values.add(iv);
        threadLocalValues.set(values);
    }


    class KeyMessageListener extends AppRedisMessageSubcriber {

        public KeyMessageListener(RedisClient client) {
            super(client);
        }

        @Override
        public void onSubcrible(MessageData message) {
            if (message == null) {
                return;
            }
            Integer mutex = mutexMap.get(message.getKey());
            if (mutex == null) {
                return;
            }
            synchronized (mutex) {
                try {
                    mutex.notifyAll();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }


    @Data
    class IndentifyValue {

        private String key;

        private String value;

    }


}
