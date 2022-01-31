package com.uifuture.ssm.mq.aliyun.ons;

import com.aliyun.openservices.ons.api.Action;
import com.uifuture.ssm.mq.aliyun.ConsumeResult;

/**
 * 阿里云的返回状态与自定义的转换器
 */
public class ComsumeResultAdapter {
    public static Action getResult(ConsumeResult result) {
        if (result == null) {
            return Action.CommitMessage;
        }
        if (result.getCode().equals(ConsumeResult.CommitMessage.getCode())) {
            return Action.CommitMessage;
        }
        if (result.getCode().equals(ConsumeResult.ReconsumeLater.getCode())) {
            return Action.ReconsumeLater;
        }
        return Action.CommitMessage;
    }
}
