/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.singleton.eager;

/**
 * 饿汉式实现方式
 *
 * @author chenhx
 * @version StaticThreadSafeSingleton.java, v 0.1 2018-07-29 下午 1:53
 */
public class EagerSingleton {
    /**
     * 静态的内部变量
     */
    private static EagerSingleton eagerSingleton = new EagerSingleton();

    /**
     * 将构造方法设置为私有，不让外部访问
     */
    private EagerSingleton() {
    }

    /**
     * 实现一个全局可以访问的方法，通过该方法可以获取该单例对象
     *
     * @return
     */
    public static EagerSingleton getInstance() {
        return eagerSingleton;
    }
}