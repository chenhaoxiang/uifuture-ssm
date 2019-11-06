/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.staticproxy.target;

/**
 * 委托类
 * @author chenhx
 * @version Alipay.java, v 0.1 2018-07-24 下午 8:19
 */
public class AliPay implements Pay {
    @Override
    public void pay(String operation) {
        System.out.println("进行支付,操作：" + operation);
    }
}