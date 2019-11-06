/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.spring.aop.annotation;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试注解AOP方式
 *
 * @author chenhx
 * @version RentingServiceImplTest.java, v 0.1 2019-07-30 19:33 chenhx
 */
public class RentingServiceImplTest {

    @Test
    public void service() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-content.xml");
//        RentingServiceImpl rentingServiceImpl = applicationContext.getBean("rentingServiceImpl",RentingService.class);
//注意不要使用实现类来进行强转，Spring AOP 部分使用JDK 动态代理或者 CGLIB 来为目标对象创建代理。如果被代理的目标实现了至少一个接口则会使用JDK 动态代理。
// java.lang.ClassCastException: com.sun.proxy.$Proxy17 cannot be cast to com.uifuture.spring.aop.annotation.impl.RentingServiceImpl
        RentingService rentingService = applicationContext.getBean("rentingServiceImpl", RentingService.class);
        rentingService.service();
    }

}
