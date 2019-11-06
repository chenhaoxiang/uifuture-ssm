/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.factorydemo;

import com.uifuture.factorydemo.api.Worker;
import com.uifuture.factorydemo.factory.WorkerFactory;

/**
 * 测试类
 *
 * @author chenhx
 * @version TestFactory.java, v 0.1 2018-07-19 下午 10:55
 */
public class TestFactory {
    public static void main(String[] args) {
        Worker worker = WorkerFactory.getWorker();
        worker.work();
    }
}