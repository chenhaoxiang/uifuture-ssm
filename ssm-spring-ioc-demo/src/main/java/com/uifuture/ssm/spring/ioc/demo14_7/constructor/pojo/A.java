package com.uifuture.ssm.spring.ioc.demo14_7.constructor.pojo;

/**
 * 循环依赖
 */
public class A {
    private B b;

    public A(B b) {
        this.b = b;
    }
}
