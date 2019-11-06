/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.factory.simple;

/**
 * 简单工厂方法的测试
 *
 * @author chenhx
 * @version TestPayFactory.java, v 0.1 2018-07-29 下午 3:02
 */
public class TestPayFactory {
    public static void main(String[] args) {
        //传入不同的type值，运行查看输出结果是否与预期的一致
        Pay aliPay = PayFactory.getPay("ali");
        aliPay.pay();
        Pay wxPay = PayFactory.getPay("wx");
        wxPay.pay();
    }
}