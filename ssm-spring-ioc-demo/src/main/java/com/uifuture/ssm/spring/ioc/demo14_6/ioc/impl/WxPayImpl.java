package com.uifuture.ssm.spring.ioc.demo14_6.ioc.impl;

import com.uifuture.ssm.spring.ioc.demo14_6.ioc.Pay;

/**
 * 微信支付
 */
public class WxPayImpl implements Pay {
    @Override
    public void pay() {
        System.out.println("微信支付");
    }
}