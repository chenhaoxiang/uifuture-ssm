/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.factory.abstractfactory.concreteproduct;

import com.uifuture.factory.abstractfactory.abstractproduct.AliPay;

/**
 * 安卓系统下的支付宝具体支付产品，由工厂负责生产的具体对象
 *
 * @author chenhx
 * @version AndroidAliPay.java, v 0.1 2018-07-29 下午 5:14
 */
public class AndroidAliPay implements AliPay {
    @Override
    public void appPay() {
        System.out.println("安卓系统下的支付宝app支付");
    }
}