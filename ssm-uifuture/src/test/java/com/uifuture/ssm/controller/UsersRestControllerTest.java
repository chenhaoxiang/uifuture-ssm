/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.controller;

import com.uifuture.ssm.BaseTest;
import com.uifuture.ssm.convert.UsersConvert;
import com.uifuture.ssm.dto.UsersCookieDTO;
import com.uifuture.ssm.entity.UsersEntity;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author chenhx
 * @version UsersRestControllerTest.java, v 0.1 2019-09-17 18:07 chenhx
 */
public class UsersRestControllerTest extends BaseTest {

    public static void main(String[] args) {
        int size = 1000000;
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setQqOpenid("");
        usersEntity.setHeadImage("");
        usersEntity.setUsername("");
        usersEntity.setSignature("");
        usersEntity.setPassword("");
        usersEntity.setSalt("");
        usersEntity.setMobilePhone("");
        usersEntity.setSex(0);
        usersEntity.setBirthday(LocalDate.now());
        usersEntity.setEmail("");
        usersEntity.setWeixin("");
        usersEntity.setQq("");
        usersEntity.setUb(0);
        usersEntity.setAlipayAccountNumber("");
        usersEntity.setAlipayRealName("");
        usersEntity.setRealNameState(0);
        usersEntity.setDescription("");
        usersEntity.setUpdateId(0);
        usersEntity.setType(0);
        usersEntity.setState(0);
        usersEntity.setCreateId(0);
        usersEntity.setMailboxState(0);
        usersEntity.setId(0);
        usersEntity.setCreateTime(new Date());
        usersEntity.setUpdateTime(new Date());
        usersEntity.setDeleteTime(0);

        long s = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            UsersCookieDTO usersCookieDTO = new UsersCookieDTO();
            BeanUtils.copyProperties(usersEntity, usersCookieDTO);
        }
        long e = System.currentTimeMillis();
        System.out.println("SpringBean拷贝" + size + "次，共消耗时间:" + (e - s) + "毫秒");
        s = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            UsersCookieDTO usersCookieDTO = UsersConvert.INSTANCE.entityToDTO(usersEntity);
        }
        e = System.currentTimeMillis();
        System.out.println("mapstruct拷贝" + size + "次，共消耗时间:" + (e - s) + "毫秒");
    }

}
