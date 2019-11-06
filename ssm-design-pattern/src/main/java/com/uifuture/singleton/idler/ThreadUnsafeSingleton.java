/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.singleton.idler;

/**
 * 线程不安全的懒汉式实现方式
 *
 * @author chenhx
 * @version ThreadUnsafeSingleton.java, v 0.1 2018-07-29 下午 12:52
 */
public class ThreadUnsafeSingleton {
    /**
     * 类的内部自己定义一个对象，注意是私有对象
     */
    private static ThreadUnsafeSingleton threadUnsafeSingleton;

    /**
     * 将构造方法设置为私有，不让外部访问
     */
    private ThreadUnsafeSingleton() {
    }

    /**
     * 接下来就是实现一个全局可以访问的方法，通过该方法可以获取该单例对象
     *
     * @return
     */
    public static ThreadUnsafeSingleton getInstance() {
        if (threadUnsafeSingleton == null) {
            threadUnsafeSingleton = new ThreadUnsafeSingleton();
        }
        return threadUnsafeSingleton;
    }
}