/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.dynamicproxy.jdk.target;

/**
 * 委托类 - 另一种支付方式
 *
 * @author chenhx
 * @version WxPay.java, v 0.1 2018-07-24 下午 8:56
 */
public class WxPay implements Pay {
    @Override
    public void pay(String operation) {
        System.out.println("进行WxPay支付,操作：" + operation);
    }
}