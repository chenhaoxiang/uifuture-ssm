/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.dynamicproxy.jdk.target;


/**
 * 委托类  - 一种支付方式
 *
 * @author chenhx
 * @version Alipay.java, v 0.1 2018-07-24 下午 8:55
 */
public class AliPay implements Pay {
    @Override
    public void pay(String operation) {
        System.out.println("进行AliPay支付,操作：" + operation);
    }
}