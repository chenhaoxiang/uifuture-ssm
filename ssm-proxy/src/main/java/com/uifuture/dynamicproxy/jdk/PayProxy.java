/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.dynamicproxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * JDK动态代理类
 *
 * @author chenhx
 * @version PayProxy.java, v 0.1 2018-07-24 下午 8:58
 */
public class PayProxy implements InvocationHandler {
    private Object target;

    /**
     * 构造方法，需要实现代理的真实对象
     *
     * @param target
     */
    public PayProxy(Object target) {
        this.target = target;
    }

    /**
     * 负责处理动态代理类上的方法调用
     * 根据三个参数进行预处理或者分派到不同的委托类实例上使用反射进行执行
     *
     * @param proxy  被代理的对象
     * @param method 要调用的方法
     * @param args   方法调用时所需要的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //在进行执行目标方法前可以进行操作
        System.out.println("调用之前...");
        System.out.println("Method:" + method);
        //调用真实对象的方法，会跳转到代理对象关联的handler对象的invoke方法来进行调用，内部通过类反射实现
        Object result = method.invoke(target, args);
        //在进行执行目标方法后可以进行操作
        System.out.println("调用之后...");
        return result;
    }
}