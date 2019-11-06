package com.uifuture.ssm.redis.listener;

import lombok.Data;

@Data
public class MessageData {

    private String app;

    private String key;

    private String data;

    private String clazz;

    private int expire;

}
