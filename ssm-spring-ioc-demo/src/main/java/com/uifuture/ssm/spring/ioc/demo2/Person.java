/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.ssm.spring.ioc.demo2;

import com.uifuture.ssm.spring.ioc.entity.ObjectTarget;

/**
 * @author chenhx
 * @version Person.java, v 0.1 2018-12-26 下午 8:08 chenhx
 */
public class Person {
    private ObjectTarget objectTarget;

    public Person(ObjectTarget objectTarget) {
        this.objectTarget = objectTarget;
    }

    /**
     * 通过IoC思想获取一个对象
     *
     * @return
     */
    public ObjectTarget getObjectTarget() {
        System.out.println("I find a objectTarget:" + objectTarget.toString());
        return objectTarget;
    }

    /**
     * Setter method for property <tt>objectTarget</tt>.
     *
     * @param objectTarget value to be assigned to property objectTarget
     */
    public void setObjectTarget(ObjectTarget objectTarget) {
        this.objectTarget = objectTarget;
    }
}