/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm;

import com.uifuture.ssm.aliyun.model.UbBodyModel;
import com.uifuture.ssm.aliyun.mq.SendMQ;
import com.uifuture.ssm.dto.UsersPayDTO;
import com.uifuture.ssm.enums.MqTypeEnum;
import com.uifuture.ssm.enums.PayTypeEnNameEnum;
import com.uifuture.ssm.util.PasswordUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * 消息发送测试
 *
 * @author chenhx
 * @version MqTest.java, v 0.1 2019-11-14 17:53 chenhx
 */
@Slf4j
public class MqTest extends BaseTest {
    @Autowired
    private SendMQ sendMQ;

    @Test
    public void sendMQ() {
        UbBodyModel ubBodyModel = new UbBodyModel();
        ubBodyModel.setMqTypeEnum(MqTypeEnum.ADD_UB_ERROR);
        UsersPayDTO usersPayDTO = new UsersPayDTO();
        usersPayDTO.setMoney(new BigDecimal("0"));
        usersPayDTO.setInfo("测试测试");
        usersPayDTO.setEmail("ceshi@qq.com");
        usersPayDTO.setState(0);
        usersPayDTO.setStateStr("状态");
        usersPayDTO.setPayTypeEnName(PayTypeEnNameEnum.ALI_APY.getValue());
        usersPayDTO.setPayNum("");
        usersPayDTO.setCustom(0);
        usersPayDTO.setMobile(0);
        usersPayDTO.setDevice("");
        usersPayDTO.setTokenNum(PasswordUtils.getToken());
        usersPayDTO.setPassUrl("");
        usersPayDTO.setBackUrl("");
        usersPayDTO.setCloseUrl("");
        usersPayDTO.setOrderNumber("");
        usersPayDTO.setUsersId(0);
        ubBodyModel.setData(usersPayDTO);
        sendMQ.send(ubBodyModel);
    }


}
