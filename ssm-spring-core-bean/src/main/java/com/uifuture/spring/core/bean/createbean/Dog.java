/**
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.spring.core.bean.createbean;

/**
 * @author chenhx
 * @version Dog.java, v 0.1 2019-03-13 22:26 chenhx
 */
public class Dog implements Animal {
    private String age;

    @Override
    public void say() {
        System.out.println("i am dog.age=" + age);
    }

    public void setAge(String age) {
        this.age = age;
    }
}