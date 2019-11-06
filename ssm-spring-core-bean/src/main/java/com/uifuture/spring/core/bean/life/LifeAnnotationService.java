/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.spring.core.bean.life;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 使用注解来指定初始化和销毁方法
 *
 * @author chenhx
 * @version LifeAnnotationService.java, v 0.1 2019-02-25 20:08 chenhx
 */
public class LifeAnnotationService {

    /**
     * 销毁方法
     *
     * @throws Exception
     */
    @PreDestroy
    public void preDestroy() throws Exception {
        System.out.println("执行preDestroy注解标注的方法");
    }

    /**
     * 初始化方法
     *
     * @throws Exception
     */
    @PostConstruct
    public void initPostConstruct() throws Exception {
        System.out.println("执行PostConstruct注解标注的方法");
    }
}