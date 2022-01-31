/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.aliyun.mq;

import com.alibaba.fastjson.JSON;
import com.uifuture.ssm.aliyun.model.UbBodyModel;
import com.uifuture.ssm.mq.aliyun.ons.ONSProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author chenhx
 * @version SenMQ.java, v 0.1 2019-11-14 17:48 chenhx
 */
@Slf4j
@Service
public class SendMQ {

    @Value("${aliyun.mq.uifuture.tag}")
    private String versionTag;

    @Resource(name = "onsProducer")
    private ONSProducer onsProducer;

    public void send(UbBodyModel mqModel) {
        if (mqModel == null) {
            log.info("发送消息队列，数据为NULL,不进行发送消息队列，{}", JSON.toJSONString(mqModel));
            return;
        }
        String id = UUID.randomUUID().toString();
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("data", mqModel);
            onsProducer.send(map, id, versionTag);
            log.info("发送消息队列成功，map={},id={}", JSON.toJSONString(mqModel), id);
        } catch (Exception e) {
            log.error("发送消息队列失败, " + JSON.toJSONString(mqModel), e);
            //等待优化 - 最好是放入延时队列，因为这种情况的失败，一般是阿里云的波动引起的，短时间的调用可能同样失败
            try {
                Thread.sleep(3000);
                onsProducer.send(map, id, versionTag);
                log.info("重新发送消息队列成功，map={},id={}", JSON.toJSONString(mqModel), id);
            } catch (Exception e1) {
                log.error("发送消息队列失败重试后又失败, " + JSON.toJSONString(mqModel) + ",id=" + id, e);
            }
        }
    }

}
