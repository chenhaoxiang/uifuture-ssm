/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.factory.simple;

/**
 * 支付方式的公共父接口
 *
 * @author chenhx
 * @version Pay.java, v 0.1 2018-07-29 下午 2:49
 */
public interface Pay {
    /**
     * 支付公共接口方法
     */
    void pay();
    //另外还有退款，关闭订单，查询等等公共方法，在此不进行写了
}
