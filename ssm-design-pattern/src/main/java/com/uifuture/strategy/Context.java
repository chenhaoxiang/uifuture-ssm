/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.strategy;

import com.uifuture.strategy.impl.EveningStrategyImpl;
import com.uifuture.strategy.impl.MorningStrategyImpl;
import com.uifuture.strategy.impl.NooningStrategyImpl;

import java.math.BigDecimal;

/**
 * 根据时间选择不同的策略类
 *
 * @author chenhx
 * @version Context.java, v 0.1 2018-07-31 下午 7:35
 */
public class Context {
    private Strategy strategy;

    /**
     * 构造函数，根据不同的具体策略赋值给strategy
     *
     * @param strategy
     */
    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    /**
     * 构造函数，根据不同的参数，进行实现不同的具体策略
     *
     * @param timeName
     */
    public Context(String timeName) {
        //不建议使用魔法值，请在实际开发中定义一个枚举类
        if ("evening".equals(timeName)) {
            this.strategy = new EveningStrategyImpl();
        } else if ("nooning".equals(timeName)) {
            this.strategy = new NooningStrategyImpl();
        } else if ("morning".equals(timeName)) {
            this.strategy = new MorningStrategyImpl();
        }
    }

    /**
     * 执行促销活动方法
     *
     * @param price
     * @return
     */
    public BigDecimal execute(BigDecimal price) {
        return strategy.discount(price);
    }
}