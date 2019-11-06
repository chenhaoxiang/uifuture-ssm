/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.spring.aop.xml.service.impl;

import com.uifuture.spring.aop.xml.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author chenhx
 * @version UserServiceImplTest.java, v 0.1 2019-06-27 20:03 chenhx
 */
public class UserServiceImplTest {
    @Test
    public void say() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-content.xml");
        UserService userService = applicationContext.getBean("userServiceImpl", UserService.class);
        userService.say("你好");
    }
}
