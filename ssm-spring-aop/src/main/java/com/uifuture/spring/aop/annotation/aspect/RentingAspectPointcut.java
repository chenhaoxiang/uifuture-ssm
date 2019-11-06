/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.spring.aop.annotation.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 中介所需要做的事情
 * 代码清单16-20
 *
 * @author chenhx
 * @version RentingAspect.java, v 0.1 2019-07-24 20:55 chenhx
 */
@Component
@Aspect
public class RentingAspectPointcut {

    @Pointcut("execution(* com.uifuture.spring.aop.annotation.impl.RentingServiceImpl.service())")
    public void pointcutService() {
    }

    @Before("pointcutService()")
    public void before() {
        System.out.println("带租客看房");
        System.out.println("谈价格");
    }

    @After("pointcutService()")
    public void after() {
        System.out.println("交钥匙");
    }

}
