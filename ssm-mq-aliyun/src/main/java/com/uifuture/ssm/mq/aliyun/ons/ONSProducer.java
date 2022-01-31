package com.uifuture.ssm.mq.aliyun.ons;


import com.uifuture.ssm.mq.aliyun.MQProducer;

import java.util.Map;

/**
 * 生产者实现类
 */
public class ONSProducer implements MQProducer {
    /**
     * 实际生产者
     */
    private ONSProducerInvoker invoker;
    private String topic = "";

    @Override
    public void send(Map<String, Object> body, String keys) {
        invoker.send(topic, body, keys);
    }

    @Override
    public void send(Map<String, Object> body, String keys, String tags) {
        invoker.send(topic, body, keys, tags);
    }

    @Override
    public void send(Map<String, Object> body, String keys, Long delay) {
        invoker.send(topic, body, keys, delay);
    }

    @Override
    public void send(Map<String, Object> body, String keys, String tags, Long delay) {
        invoker.send(topic, body, keys, tags, delay);
    }

    public void setInvoker(ONSProducerInvoker invoker) {
        this.invoker = invoker;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
