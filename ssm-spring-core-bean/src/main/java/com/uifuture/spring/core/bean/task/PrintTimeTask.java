/*
 * copyfuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.spring.core.bean.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 定时任务类
 *
 * @author chenhx
 * @version PrintTimeTask.java, v 0.1 2019-04-29 19:56 chenhx
 */
@Component
public class PrintTimeTask {
    @Scheduled(fixedDelay = 10000)
    public void printTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        System.out.println(formatter.format(date));
    }
}