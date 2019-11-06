/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.ssm.spring.ioc.demo1;

import com.uifuture.ssm.spring.ioc.entity.ObjectTarget;

/**
 * @author chenhx
 * @version Person.java, v 0.1 2018-12-26 下午 8:08 chenhx
 */
public class Person {
    /**
     * 获取一个对象
     *
     * @return
     */
    public ObjectTarget getObjectTarget() {
        ObjectTarget objectTarget = new ObjectTarget();
        //...
        System.out.println("I find a objectTarget:" + objectTarget.toString());
        return objectTarget;
    }
}