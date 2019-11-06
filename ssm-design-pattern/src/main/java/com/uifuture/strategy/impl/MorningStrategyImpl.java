/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.strategy.impl;

import com.uifuture.strategy.Strategy;

import java.math.BigDecimal;

/**
 * 早上时间段具体的打折策略角色
 *
 * @author chenhx
 * @version MorningStrategyImpl.java, v 0.1 2018-07-30 下午 10:12
 */
public class MorningStrategyImpl extends Strategy {
    /**
     * 在实际应用中，可能还会有另外的一系列操作
     * @param price 实际价格
     * @return
     */
    @Override
    public BigDecimal discount(BigDecimal price) {
        System.out.println("实际价格:" + price.doubleValue());
        //直接返回折扣的金额
        return price.multiply(new BigDecimal(0.8));
    }
}