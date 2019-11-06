/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.spring.ioc.demo14_7.interface_injection.code_14_35;

interface InterfaceB {
    void doSomething();
}

/**
 * 接口注入的原始雏形
 *
 * @author chenhx
 * @version ClassA.java, v 0.1 2019-02-13 23:41 chenhx
 */
public class ClassA {
    private InterfaceB clzB;

    public void doSomething() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Object obj = Class.forName("从配置文件中获取类名").newInstance();
        clzB = (InterfaceB) obj;
        clzB.doSomething();
    }

}