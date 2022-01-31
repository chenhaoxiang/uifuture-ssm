package com.uifuture.ssm.mq.aliyun;


import java.util.Map;

/**
 * 所有消息都将被转成fastJon形式发送
 */
public interface MQProducer {

    /**
     * 发送消息
     *
     * @param body 消息的内容
     * @param keys 消息的唯一主键,可以是ID或者自动生成的标识,用于追踪消息
     */
    void send(Map<String, Object> body, String keys);

    /**
     * 发送消息
     *
     * @param body 消息的内容
     * @param keys 消息的唯一主键,可以是ID或者自动生成的标识,用于追踪消息
     * @param tags 标签,可以区分不同的接收者
     */
    void send(Map<String, Object> body, String keys, String tags);

    /**
     * 发送消息, 注意RocketMQ需要额外配置
     *
     * @param body  消息的内容
     * @param keys  消息的唯一主键,可以是ID或者自动生成的标识,用于追踪消息
     * @param delay 消息延迟消费, ONS中是秒, RocketMQ中是delayLevel
     */
    void send(Map<String, Object> body, String keys, Long delay);

    /**
     * 发送消息, 注意RocketMQ需要额外配置
     *
     * @param body  消息的内容
     * @param keys  消息的唯一主键,可以是ID或者自动生成的标识,用于追踪消息
     * @param tags  标签,可以区分不同的接收者
     * @param delay delay 消息延迟消费, ONS中是秒, RocketMQ中是delayLevel
     */
    void send(Map<String, Object> body, String keys, String tags, Long delay);
}
