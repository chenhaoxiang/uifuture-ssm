/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.dynamicproxy.jdk;

import com.uifuture.dynamicproxy.jdk.target.AliPay;
import com.uifuture.dynamicproxy.jdk.target.Pay;

/**
 * 进行调用优化后的代理类
 * @author chenhx
 * @version StoreOptimize.java, v 0.1 2018-07-24 下午 9:53
 */
public class StoreOptimize {
    public static void main(String[] args) {
        Pay realPay = new AliPay();
        Pay aliPay = new PayProxyOptimize().create(Pay.class, realPay);
        aliPay.pay("测试");
        Pay aliPay2 = new PayProxyOptimize().create2(Pay.class, realPay,
                realPay.getClass().getInterfaces()
        );
        aliPay2.pay("测试2");
    }
}