/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.singleton.idler;

/**
 * 线程安全的懒汉式实现方式
 *
 * @author chenhx
 * @version ThreadUnsafeSingleton.java, v 0.1 2018-07-29 下午 13:10
 */
public class ThreadSafeSingleton {
    /**
     * 类的内部自己定义一个对象，注意是私有对象
     */
    private static ThreadSafeSingleton threadSafeSingleton;

    /**
     * 将构造方法设置为私有，不让外部访问
     */
    private ThreadSafeSingleton() {
    }

    /**
     * 接下来依旧实现一个全局可以访问的方法，通过该方法可以获取该单例对象
     * 但是在该方法上我们进行加锁
     *
     * @return
     */
    public static synchronized ThreadSafeSingleton getInstance() {
        if (threadSafeSingleton == null) {
            threadSafeSingleton = new ThreadSafeSingleton();
        }
        return threadSafeSingleton;
    }
}