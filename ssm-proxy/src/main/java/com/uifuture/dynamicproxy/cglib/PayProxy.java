/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.dynamicproxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 使用CGLIB动态代理的代理类
 * 首先实现一个MethodInterceptor，方法调用会被转发到该类的intercept()方法。
 *
 * @author chenhx
 * @version PayProxy.java, v 0.1 2018-07-27 下午 7:00
 */
public class PayProxy<T> implements MethodInterceptor {
    private Enhancer enhancer = new Enhancer();

    public T getInstance(Class clazz) {
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return (T) enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("代理开始");
        //调用真实对象的方法
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("代理结束");
        return result;
    }
}