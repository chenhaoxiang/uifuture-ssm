/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.factory.method.impl;

import com.uifuture.factory.method.Pay;

/**
 * 微信支付方式
 *
 * @author chenhx
 * @version WxPayImpl.java, v 0.1 2018-07-29 下午 2:51
 */
public class WxPayImpl implements Pay {
    @Override
    public void pay() {
        System.out.println("进行微信支付");
    }
}