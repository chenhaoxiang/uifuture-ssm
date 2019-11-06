/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.dynamicproxy.cglib;

/**
 * 进行cglib代理的测试
 *
 * @author chenhx
 * @version TestProxy.java, v 0.1 2018-07-27 下午 7:12
 */
public class TestProxy {
    public static void main(String[] args) {
        PayProxy<AliPay> payProxy = new PayProxy();
        AliPay aliPay = payProxy.getInstance(AliPay.class);
        aliPay.pay("测试cglib动态代理");
    }
}