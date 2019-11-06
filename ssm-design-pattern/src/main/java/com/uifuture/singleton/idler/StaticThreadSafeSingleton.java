/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.singleton.idler;

/**
 * 静态内部类懒汉式实现方式
 *
 * @author chenhx
 * @version StaticThreadSafeSingleton.java, v 0.1 2018-07-29 下午 1:53
 */
public class StaticThreadSafeSingleton {
    /**
     * 将构造方法设置为私有，不让外部访问
     */
    private StaticThreadSafeSingleton() {
    }

    /**
     * 实现一个全局可以访问的方法，通过该方法可以获取该单例对象
     *
     * @return
     */
    public static StaticThreadSafeSingleton getInstance() {
        return HolderClass.staticThreadSafeSingleton;
    }

    /**
     * 类的内部自己定义一个类，注意是私有类
     */
    private static class HolderClass {
        /**
         * 静态内部类中定义一个私有变量，对该变量进行了单例实现
         */
        private final static StaticThreadSafeSingleton staticThreadSafeSingleton = new StaticThreadSafeSingleton();
    }
}