/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.spring.core.bean.config;

import com.uifuture.spring.core.bean.dao.UserDao;
import com.uifuture.spring.core.bean.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Bean的配置类
 * Configuration注解将一个POJO标注为定义Bean的配置类
 *
 * @author chenhx
 * @version SpringBeanConfig.java, v 0.1 2019-03-05 18:59 chenhx
 */
@Configuration
public class SpringBeanConfig {
    /**
     * 定义了userDao，注入到Spring容器中
     *
     * @return
     */
    @Bean
    public UserDao userDao() {
        return new UserDao();
    }


    /**
     * UserServiceImpl依赖了UserDao，所以可以在这里直接显示调用包含UserDao的构造方法
     *
     * @return
     */
    @Bean
    public UserServiceImpl userService(UserDao userDao) {
        return new UserServiceImpl(userDao);
    }

    /**
     * UserServiceImpl依赖了UserDao，还可以通过在方法中进行调用setter的方式注入userDao
     *
     * @return
     */
    @Bean
    public UserServiceImpl userService() {
        UserServiceImpl userService = new UserServiceImpl();
        userService.setUserDao(userDao());
        return userService;
    }

}