/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.spring.core.bean.life;

/**
 * XML配置Bean的初始化动作以及销毁动作
 *
 * @author chenhx
 * @version LifeXmlService.java, v 0.1 2019-02-25 19:19 chenhx
 */
public class LifeXmlService {

    /**
     * 通过<bean>的destroy-method属性指定的销毁方法
     *
     * @throws Exception
     */
    public void destroyMethod() throws Exception {
        System.out.println("执行XML配置的destroy-method方法");
    }

    /**
     * 通过<bean>的init-method属性指定的初始化方法
     *
     * @throws Exception
     */
    public void initMethod() throws Exception {
        System.out.println("执行XML配置的init-method方法");
    }
}