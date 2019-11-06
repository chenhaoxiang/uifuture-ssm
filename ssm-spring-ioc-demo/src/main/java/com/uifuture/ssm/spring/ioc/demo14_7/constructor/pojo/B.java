package com.uifuture.ssm.spring.ioc.demo14_7.constructor.pojo;

/**
 * 循环依赖
 */
public class B {
    private A a;

    public B(A a) {
        this.a = a;
    }
}
