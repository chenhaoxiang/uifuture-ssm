/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.spring.aop.xml.service.impl;

import com.uifuture.spring.aop.xml.service.UserService;

/**
 * @author chenhx
 * @version UserServiceImpl.java, v 0.1 2019-06-27 19:45 chenhx
 */
public class UserServiceImpl implements UserService {
    @Override
    public String say(String name) {
        System.out.println("进入UserServiceImpl方法");
        return "say" + name;
    }
}
