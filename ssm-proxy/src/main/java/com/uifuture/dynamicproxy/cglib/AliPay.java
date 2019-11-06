/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.dynamicproxy.cglib;

/**
 * 目标类
 *
 * @author chenhx
 * @version AliPay.java, v 0.1 2018-07-27 下午 6:57
 */
public class AliPay {
    public void pay(String operation) {
        System.out.println("进行AliPay支付,操作：" + operation);
    }
}