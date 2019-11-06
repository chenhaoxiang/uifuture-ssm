/*
 * copyfuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.spring.core.bean.entity;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author chenhx
 * @version TaskTest.java, v 0.1 2019-04-29 20:03 chenhx
 */
public class TaskTest {

    /**
     * 这个before方法在所有的测试方法之前执行，并且只执行一次
     * 所有做Junit单元测试时一些初始化工作可以在这个方法里面进行
     * 比如在before方法里面初始化ApplicationContext和userService
     */
    @Before
    public void before() {
        //使用spring配置文件创建Spring上下文
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-content-task.xml");
    }

    /**
     * 测试定时任务的执行
     *
     * @throws InterruptedException
     */
    @Test
    public void printTime() throws InterruptedException {
        System.out.println("开始执行了...");
        Thread.sleep(1000000);
        System.out.println("结束执行了...");
    }

}