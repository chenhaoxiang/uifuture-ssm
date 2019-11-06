/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.spring.aop.xml.service;

/**
 * 用户服务
 * 使用jdk动态代理
 *
 * @author chenhx
 * @version UserService.java, v 0.1 2019-06-27 19:44 chenhx
 */
public interface UserService {

    /**
     * 业务接口
     *
     * @param name
     * @return
     */
    String say(String name);
}
