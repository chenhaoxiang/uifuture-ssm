/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.email.impl;

import com.uifuture.ssm.BaseTest;
import com.uifuture.ssm.email.EmailConfig;
import com.uifuture.ssm.email.SendEmail;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试发送邮件
 *
 * @author chenhx
 * @version SendEmailCallableTest.java, v 0.1 2019-09-16 23:10 chenhx
 */
public class SendEmailCallableTest extends BaseTest {
    @Autowired
    private EmailConfig emailConfig;

    @Test
    public void sendEmail() throws InterruptedException {
        //发送邮件
        SendEmail sendEmail = new SendEmail() {
            @Override
            public String getCode() {
                return "123456";
            }

            @Override
            public String getName() {
                return "test";
            }

            @Override
            public String getEmail() {
                return "接收邮件的邮箱地址";
            }
        };
        //异步发送邮件
        SendEmailCallable sendEmailCallable = new SendEmailCallable(emailConfig, sendEmail);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(sendEmailCallable);
        //因为是异步发送，主线程如果退出，子线程会直接退出
        Thread.sleep(60000);
    }
}
