/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.factory.method.impl;

import com.uifuture.factory.method.Pay;
import com.uifuture.factory.method.PayFactory;

/**
 * WxPayImpl的具体工厂类
 *
 * @author chenhx
 * @version WxPayImplFactory.java, v 0.1 2018-07-29 下午 3:40
 */
public class WxPayImplFactory implements PayFactory {
    @Override
    public Pay getPay() {
        return new WxPayImpl();
    }
}