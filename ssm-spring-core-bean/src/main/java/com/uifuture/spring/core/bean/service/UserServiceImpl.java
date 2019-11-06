/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.spring.core.bean.service;

import com.uifuture.spring.core.bean.dao.UserDao;
import org.springframework.stereotype.Component;

/**
 * 通过注解定义一个Bean
 *
 * @author chenhx
 * @version UserServiceImpl.java, v 0.1 2019-03-05 18:35 chenhx
 */
@Component("userService")
public class UserServiceImpl {
    private UserDao userDao;

    public UserServiceImpl() {
    }

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Setter method for property <tt>userDao</tt>.
     *
     * @param userDao value to be assigned to property userDao
     */
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}