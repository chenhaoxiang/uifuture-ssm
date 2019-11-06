/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.dynamicproxy.jdk;

import com.uifuture.dynamicproxy.jdk.target.AliPay;
import com.uifuture.dynamicproxy.jdk.target.Pay;
import com.uifuture.dynamicproxy.jdk.target.WxPay;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 调用类 进行演示
 *
 * @author chenhx
 * @version Stor.java, v 0.1 2018-07-24 下午 9:06
 */
public class Store {
    public static void main(String[] args) {
        Pay aliPay = new AliPay();
        aliPay(aliPay);
        System.out.println("-----------");
        Pay wxPay = new WxPay();
        aliPay(wxPay);
    }

    public static void aliPay(Pay realPay) {
//          PayProxy进行实现了 InvocationHandler 接口
//          内部包含指向委托类实例的引用，用于真正执行分派转发过来的方法调用.
//         也就是要代理哪个真实对象，就将该对象传进去，最后是通过该真实对象来调用其方法
        InvocationHandler handler = new PayProxy(realPay);
        ClassLoader loader = realPay.getClass().getClassLoader();
        Class[] interfaces = realPay.getClass().getInterfaces();
//        该方法用于为指定类装载器、一组接口及调用处理器生成动态代理类实例
        Pay pay = (Pay) Proxy.newProxyInstance(loader, interfaces, handler);
        pay.pay("pay");
    }
}