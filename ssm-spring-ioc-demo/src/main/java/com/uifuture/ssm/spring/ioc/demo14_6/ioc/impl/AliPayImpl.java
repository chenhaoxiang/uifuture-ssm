package com.uifuture.ssm.spring.ioc.demo14_6.ioc.impl;

import com.uifuture.ssm.spring.ioc.demo14_6.ioc.Pay;

/**
 * 支付宝支付
 */
public class AliPayImpl implements Pay {
    @Override
    public void pay() {
        System.out.println("支付宝支付");
    }
}
