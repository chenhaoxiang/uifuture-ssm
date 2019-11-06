package com.uifuture.ssm.spring.ioc.demo14_7.constructor.service.impl;

import com.uifuture.ssm.spring.ioc.demo14_7.constructor.entity.Person;
import com.uifuture.ssm.spring.ioc.demo14_7.service.SpringIoCService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringIoCServiceImplTest {

    /**
     * 演示构造函数注入配置
     */
    @Test
    public void saySpringIoC() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-ioc-constructor.xml");

        SpringIoCService springIoCService = applicationContext.getBean("springIoCServiceImpl", SpringIoCService.class);
        springIoCService.saySpringIoC();
    }

    /**
     * 演示Person注入配置
     */
    @Test
    public void person() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-ioc-constructor.xml");

        Person person = applicationContext.getBean("person", Person.class);
        System.out.println(person);
    }
}