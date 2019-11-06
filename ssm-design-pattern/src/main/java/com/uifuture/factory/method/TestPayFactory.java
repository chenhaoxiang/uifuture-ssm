/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.factory.method;

import com.uifuture.factory.abstractfactory.abstractproduct.AliPay;
import com.uifuture.factory.abstractfactory.abstractproduct.WxPay;
import com.uifuture.factory.abstractfactory.concreatefactory.AndroidPayFactory;
import com.uifuture.factory.abstractfactory.concreatefactory.IOSFactory;
import com.uifuture.factory.abstractfactory.concreteproduct.AndroidAliPay;
import com.uifuture.factory.abstractfactory.concreteproduct.AndroidWxPay;
import com.uifuture.factory.abstractfactory.concreteproduct.IOSAliPay;
import com.uifuture.factory.abstractfactory.concreteproduct.IOSWxPay;
import com.uifuture.factory.method.impl.AliPayImplFactory;
import com.uifuture.factory.method.impl.WxPayImplFactory;
import com.uifuture.mvc.UserController;
import com.uifuture.mvc.UserView;
import com.uifuture.strategy.Strategy;
import com.uifuture.strategy.impl.EveningStrategyImpl;
import com.uifuture.strategy.impl.MorningStrategyImpl;
import com.uifuture.strategy.impl.NooningStrategyImpl;
import com.uifuture.template.AbstractTemplateRole;
import com.uifuture.template.impl.BlackTeaImpl;
import com.uifuture.template.impl.GreenTeaImpl;

import java.math.BigDecimal;

/**
 * 工厂方法模式测试类
 *
 * @author chenhx
 * @version TestPayFactory.java, v 0.1 2018-07-29 下午 3:43
 */
public class TestPayFactory {
    public static void main(String[] args) {
        //获取ali支付渠道
        PayFactory payFactory = new AliPayImplFactory();
        Pay pay = payFactory.getPay();
        pay.pay();
        //获取wx支付渠道
        PayFactory payFactory2 = new WxPayImplFactory();
        Pay pay2 = payFactory2.getPay();
        pay2.pay();
    }
}

