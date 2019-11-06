/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.spring.core.bean.condition.config;

import com.uifuture.spring.core.bean.condition.condition.LinuxCondition;
import com.uifuture.spring.core.bean.condition.condition.WindowsCondition;
import com.uifuture.spring.core.bean.condition.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenhx
 * @version UserConfig.java, v 0.1 2019-03-21 20:43 chenhx
 */
@Configuration
public class UserConfig {

    /**
     * 如果WindowsCondition的matches实现方法返回true，则注入这个bean
     *
     * @return
     */
    @Conditional({WindowsCondition.class})
    @Bean(name = "windowsUser")
    public User windowsUser() {
        User user = new User();
        user.setName("windows");
        user.setAge(10);
        return user;
    }

    /**
     * 如果LinuxCondition的matches实现方法返回true，则注入这个bean
     *
     * @return
     */
    @Conditional({LinuxCondition.class})
    @Bean("linuxUser")
    public User linuxUser() {
        User user = new User();
        user.setName("linux");
        user.setAge(20);
        return user;
    }
}