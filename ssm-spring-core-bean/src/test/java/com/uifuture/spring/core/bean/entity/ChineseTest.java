/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.spring.core.bean.entity;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author chenhx
 * @version ChineseTest.java, v 0.1 2019-02-16 15:54 chenhx
 */
public class ChineseTest {
    /**
     * 演示获取单例
     */
    @Test
    public void singleton() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-content.xml");
        Chinese objA = (Chinese) context.getBean("chinese");
        objA.setMessage("I'm object A");
        objA.getMessage();
        Chinese objB = (Chinese) context.getBean("chinese");
        objB.getMessage();
        System.out.print(objA == objB);
    }


    /**
     * prototype
     */
    @Test
    public void prototype() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-content.xml");
        Chinese objA = (Chinese) context.getBean("chinesePrototype");
        objA.setMessage("I'm object A");
        objA.getMessage();
        Chinese objB = (Chinese) context.getBean("chinesePrototype");
        objB.getMessage();
        System.out.print(objA == objB);
    }
}