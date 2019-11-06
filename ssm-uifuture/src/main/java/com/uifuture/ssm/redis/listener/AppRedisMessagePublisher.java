package com.uifuture.ssm.redis.listener;

import com.alibaba.fastjson.JSON;
import com.uifuture.ssm.redis.RedisClient;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

public class AppRedisMessagePublisher {

    private String app = "";

    @Getter
    private RedisClient client;

    public AppRedisMessagePublisher(RedisClient client) {
        this.client = client;
        app = client.getConnConfig().getApp();
    }

    public void sendKeyMessage(String channel, String key) {
        if (StringUtils.isEmpty(key)) {
            return;
        }
        MessageData d = new MessageData();
        d.setKey(key);
        d.setApp(app);
        client.getRedisTemplate().convertAndSend(channel, JSON.toJSONString(d));
    }

    public void sendKeyAndValueMessage(String channel, String key, Object obj, int expire) {
        if (StringUtils.isEmpty(key)) {
            return;
        }
        if (obj == null) {
            return;
        }
        MessageData d = new MessageData();
        d.setKey(key);
        d.setApp(app);
        d.setClazz(obj.getClass().getName());
        d.setData(JSON.toJSONString(obj));
        d.setExpire(expire);
        client.getRedisTemplate().convertAndSend(channel, JSON.toJSONString(d));
    }

}
