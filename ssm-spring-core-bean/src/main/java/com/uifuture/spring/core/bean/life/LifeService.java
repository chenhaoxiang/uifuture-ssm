/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.spring.core.bean.life;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * 实现InitializingBean接口与DisposableBean接口
 *
 * @author chenhx
 * @version LifeService.java, v 0.1 2019-02-25 19:07 chenhx
 */
public class LifeService implements InitializingBean, DisposableBean {
    @Override
    public void destroy() throws Exception {
        System.out.println("执行LifeService类、DisposableBean接口的destroy方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行LifeService类、InitializingBean接口的afterPropertiesSet方法");
    }
}