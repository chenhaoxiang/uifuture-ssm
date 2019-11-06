/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.spring.aop.annotation.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;

/**
 * 中介所需要做的事情
 * 代码清单16-19
 *
 * @author chenhx
 * @version RentingAspect.java, v 0.1 2019-07-24 20:55 chenhx
 */
//@Component
//@Aspect
public class RentingAspect {

    @Before("execution(* com.uifuture.spring.aop.annotation.impl.RentingServiceImpl.service())")
    public void before() {
        System.out.println("带租客看房");
        System.out.println("谈价格");
    }

    @After("execution(* com.uifuture.spring.aop.annotation.impl.RentingServiceImpl.service())")
    public void after() {
        System.out.println("交钥匙");
    }

}
