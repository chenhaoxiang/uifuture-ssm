/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.spring.core.bean.createbean;

/**
 * 实例工厂类
 *
 * @author chenhx
 * @version AnimalBeanFactory.java, v 0.1 2019-03-13 22:24 chenhx
 */
public class AnimalBeanExampleFactory {
    /**
     * name参数决定哪个是Bean的实例
     *
     * @param name
     * @return
     */
    public Animal getAnimal(String name) {
        if ("dog".equalsIgnoreCase(name)) {
            return new Dog();
        } else if ("cat".equalsIgnoreCase(name)) {
            return new Cat();
        }
        return null;
    }

}