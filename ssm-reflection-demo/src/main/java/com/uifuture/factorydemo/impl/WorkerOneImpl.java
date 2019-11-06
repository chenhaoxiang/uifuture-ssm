/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.factorydemo.impl;

import com.uifuture.factorydemo.api.Worker;

/**
 * 实现类一
 *
 * @author chenhx
 * @version WorkerOneImpl.java, v 0.1 2018-07-19 下午 10:48
 */
public class WorkerOneImpl implements Worker {
    @Override
    public void work() {
        //输出信息方便在控制台识别实际运行的类
        System.out.println("WorkerOneImpl...");
    }
}