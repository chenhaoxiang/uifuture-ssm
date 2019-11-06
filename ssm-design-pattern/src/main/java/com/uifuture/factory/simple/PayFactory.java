/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.factory.simple;

import com.uifuture.factory.simple.impl.AliPayImpl;
import com.uifuture.factory.simple.impl.WxPayImpl;

/**
 * 支付的工厂方法
 *
 * @author chenhx
 * @version PayFactory.java, v 0.1 2018-07-29 下午 2:55
 */
public class PayFactory {
    /**
     * 通过传入参数，根据参数的不同分别返回不同的实例对象
     *
     * @param type
     * @return
     */
    public static Pay getPay(String type) {
        Pay pay = null;
        if (type.equals("ali")) {
            pay = new AliPayImpl();
        } else if (type.equals("wx")) {
            pay = new WxPayImpl();
        }
        return pay;
    }
}