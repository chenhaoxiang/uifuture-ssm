package com.uifuture.ssm.redis.listener;

import com.alibaba.fastjson.JSON;
import com.uifuture.ssm.redis.RedisClient;
import lombok.Getter;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

public abstract class AppRedisMessageSubcriber implements MessageListener {


    private String app = "";

    @Getter
    private RedisClient client;

    public AppRedisMessageSubcriber(RedisClient client) {
        this.client = client;
        app = client.getConnConfig().getApp();
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        byte[] body = message.getBody();
        String value = (String) client.getRedisTemplate().getStringSerializer().deserialize(body);
        MessageData d = JSON.parseObject(value, MessageData.class);
        if (d == null) {
            return;
        }
        if (app.equals(d.getApp()) && d.getKey() != null) {
            onSubcrible(d);
        }
    }

    public abstract void onSubcrible(MessageData message);

}
