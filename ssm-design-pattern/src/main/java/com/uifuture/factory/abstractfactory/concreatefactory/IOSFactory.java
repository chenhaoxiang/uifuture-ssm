/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.factory.abstractfactory.concreatefactory;

import com.uifuture.factory.abstractfactory.abstractfactory.PayFactory;
import com.uifuture.factory.abstractfactory.abstractproduct.AliPay;
import com.uifuture.factory.abstractfactory.abstractproduct.WxPay;
import com.uifuture.factory.abstractfactory.concreteproduct.IOSAliPay;
import com.uifuture.factory.abstractfactory.concreteproduct.IOSWxPay;

/**
 * IOS系统下的具体支付工厂类
 *
 * @author chenhx
 * @version AndroidPayFactory.java, v 0.1 2018-07-29 下午 5:23
 */
public class IOSFactory implements PayFactory {
    /**
     * 创建IOS系统下的支付宝渠道对象
     *
     * @return
     */
    @Override
    public AliPay createAliPay() {
        return new IOSAliPay();
    }

    /**
     * 创建IOS系统下的微信支付渠道对象
     *
     * @return
     */
    @Override
    public WxPay createWxPay() {
        return new IOSWxPay();
    }
}