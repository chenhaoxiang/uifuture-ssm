/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.strategy;

import com.uifuture.strategy.impl.MorningStrategyImpl;
import com.uifuture.strategy.impl.NooningStrategyImpl;

import java.math.BigDecimal;

/**
 * 客户端测试类
 *
 * @author chenhx
 * @version TestClient.java, v 0.1 2018-07-31 下午 8:04
 */
public class TestClient {
    public static void main(String[] args) {
        BigDecimal price = new BigDecimal(20);
        Context context;
        System.out.println("----执行早上的策略----");
        context = new Context("morning");
        System.out.println("折扣后价格：" + context.execute(price).doubleValue());

        System.out.println("----执行中午的策略----");
        context = new Context("nooning");
        System.out.println("折扣后价格：" + context.execute(price).doubleValue());

        System.out.println("----执行晚上的策略----");
        context = new Context("evening");
        System.out.println("折扣后价格：" + context.execute(price).doubleValue());

        //现在使用第二种方式，进行构造.
        System.out.println("----执行早上的策略----");
        //只进行演示早上和中午的
        context = new Context(new MorningStrategyImpl());
        System.out.println("折扣后价格：" + context.execute(price).doubleValue());

        System.out.println("----执行中午的策略----");
        //只进行演示早上和中午的
        context = new Context(new NooningStrategyImpl());
        System.out.println("折扣后价格：" + context.execute(price).doubleValue());
    }
}