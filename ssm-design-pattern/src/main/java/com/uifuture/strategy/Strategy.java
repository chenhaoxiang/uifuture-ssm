/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.strategy;

import java.math.BigDecimal;

/**
 * 抽象策略角色
 *
 * @author chenhx
 * @version Strategy.java, v 0.1 2018-07-30 下午 10:08
 */
public abstract class Strategy {
    /**
     * 打折活动
     *
     * @param price 实际价格
     * @return 通过打折后返回的不同价格
     */
    public abstract BigDecimal discount(BigDecimal price);
}
