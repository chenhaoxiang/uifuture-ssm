/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.strategy.impl;

import com.uifuture.strategy.Strategy;

import java.math.BigDecimal;

/**
 * 晚上时间段具体的打折策略角色
 *
 * @author chenhx
 * @version EveningStrategyImpl.java, v 0.1 2018-07-31 下午 7:27
 */
public class EveningStrategyImpl extends Strategy {
    /**
     * 晚上进行促销打折活动
     *
     * @param price 实际价格
     * @return
     */
    @Override
    public BigDecimal discount(BigDecimal price) {
        System.out.println("实际价格:" + price.doubleValue());
        //直接返回折扣的金额
        return price.multiply(new BigDecimal(0.5));
    }
}