package com.uifuture.ssm.mq.aliyun;

import java.util.Map;

/**
 * 消费者，接收消息
 * 发送方全部采取将Map转成fastJson发送
 */
public interface MQConsumer {

    /**
     * 消息的内容
     *
     * @param body
     * @return
     */
    ConsumeResult onRecived(Map<String, Object> body);
}
