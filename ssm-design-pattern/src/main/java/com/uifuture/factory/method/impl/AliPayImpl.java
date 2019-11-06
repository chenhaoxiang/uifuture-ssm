/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.factory.method.impl;

import com.uifuture.factory.method.Pay;

/**
 * 支付宝支付方式
 *
 * @author chenhx
 * @version AliPayImpl.java, v 0.1 2018-07-29 下午 2:50
 */
public class AliPayImpl implements Pay {
    @Override
    public void pay() {
        System.out.println("进行支付宝支付");
    }
}