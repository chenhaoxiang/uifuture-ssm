/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.factory.abstractfactory.abstractfactory;

import com.uifuture.factory.abstractfactory.abstractproduct.AliPay;
import com.uifuture.factory.abstractfactory.abstractproduct.WxPay;

/**
 * 抽象工厂，负责创建支付渠道，在这里是创建AliPay和WxPay
 * 注意是创建多个！-与工厂方法模式最大的区别
 *
 * @author chenhx
 * @version SystemPayFactory.java, v 0.1 2018-07-29 下午 5:19
 */
public interface PayFactory {
    /**
     * 创建AliPay对象
     *
     * @return
     */
    public AliPay createAliPay();

    /**
     * 创建WxPay对象
     *
     * @return
     */
    public WxPay createWxPay();
}
