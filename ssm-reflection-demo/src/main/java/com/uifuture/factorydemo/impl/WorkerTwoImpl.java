/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.factorydemo.impl;

import com.uifuture.factorydemo.api.Worker;

/**
 * 实现类二
 *
 * @author chenhx
 * @version WorkerOneImpl.java, v 0.1 2018-07-19 下午 10:50
 */
public class WorkerTwoImpl implements Worker {
    @Override
    public void work() {
        System.out.println("WorkerTwoImpl...");
    }
}