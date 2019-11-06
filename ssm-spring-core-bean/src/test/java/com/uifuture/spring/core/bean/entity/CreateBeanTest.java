/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.spring.core.bean.entity;

import com.uifuture.spring.core.bean.createbean.Cat;
import com.uifuture.spring.core.bean.createbean.Dog;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 静态工厂类创建Bean测试类
 *
 * @author chenhx
 * @version CreateBeanTest.java, v 0.1 2019-03-13 22:42 chenhx
 */
public class CreateBeanTest {

    /**
     * 演示 静态工厂类创建Bean测试类
     */
    @Test
    public void animalTest() {
        //加载路径下的配置文件创建ClassPathResource实例
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-content-create-bean.xml");
        Dog dog = (Dog) context.getBean("dog");
        dog.say();
        Cat cat = (Cat) context.getBean("cat");
        cat.say();
    }

    /**
     * 演示 实例工厂类创建Bean测试类
     */
    @Test
    public void animalExampleTest() {
        //加载路径下的配置文件创建ClassPathResource实例
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-content-create-bean-example.xml");
        Dog dog = (Dog) context.getBean("dog");
        dog.say();
        Cat cat = (Cat) context.getBean("cat");
        cat.say();
    }

}