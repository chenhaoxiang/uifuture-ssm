/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.spring.ioc.demo14_7.interface_injection.code_14_36;

interface InterfaceB {
    void doSomething();
}

/**
 * 容器型实现接口注入
 *
 * @author chenhx
 * @version ClassA.java, v 0.1 2019-02-13 23:54 chenhx
 */
public class ClassA {
    private InterfaceB clzB;

    public void doSomething(InterfaceB b) {
        clzB = b;
        clzB.doSomething();
    }
}