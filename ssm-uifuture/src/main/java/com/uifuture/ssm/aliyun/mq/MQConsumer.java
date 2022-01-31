/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.aliyun.mq;

import com.alibaba.fastjson.JSON;
import com.uifuture.ssm.aliyun.model.UbBodyModel;
import com.uifuture.ssm.enums.MqTypeEnum;
import com.uifuture.ssm.mq.aliyun.ConsumeResult;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author chenhx
 * @version MQConsumer.java, v 0.1 2019-11-14 17:29 chenhx
 */
@Slf4j
public class MQConsumer implements com.uifuture.ssm.mq.aliyun.MQConsumer {

    @Override
    public ConsumeResult onRecived(Map<String, Object> map) {
        log.info("消费成功，ubBodyModel={}", map);
        Object obj = map.get("data");
        if (obj == null) {
            log.info("没有消息内容,map={}", JSON.toJSONString(map));
            return ConsumeResult.CommitMessage;
        }
        if (obj instanceof UbBodyModel) {
            UbBodyModel ubBodyModel = (UbBodyModel) obj;
            if (ubBodyModel.getMqTypeEnum().getValue().equals(MqTypeEnum.ADD_UB_ERROR.getValue())) {
                log.info("添加UB消息队列消费");
            }
        }
        return ConsumeResult.CommitMessage;
    }
}
