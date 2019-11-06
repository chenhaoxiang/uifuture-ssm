/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.singleton.idler;

/**
 * 双重校验模式 DCL
 * 一种更加高效的多线程下安全的懒汉式单例模式
 *
 * @author chenhx
 * @version DCLThreadSafeSingleton.java, v 0.1 2018-07-29 下午 1:17
 */
public class DCLThreadSafeSingleton {
    /**
     * 类的内部自己定义一个对象，注意是私有对象
     * 使用volatile关键字，可以理解为轻量级的synchronized
     * 保证了变量的可见性，在JDK1.5以后保证指令重排，JDK1.5+在此例中不会出现DCL失效的情况。
     */
    private static volatile DCLThreadSafeSingleton dclThreadSafeSingleton;

    /**
     * 将构造方法设置为私有，不让外部访问
     */
    private DCLThreadSafeSingleton() {
    }

    /**
     * 接下来依旧实现一个全局可以访问的方法，通过该方法可以获取该单例对象
     *
     * @return
     */
    public static synchronized DCLThreadSafeSingleton getInstance() {
        //第一次判断空是为了synchronized不必要的同步开销，因为只有在第一次调用该方法才需要同步，以后实例不为null，是不再需要同步的。提高了性能。
        if (dclThreadSafeSingleton == null) {
            synchronized (DCLThreadSafeSingleton.class) {
                //第二次判断空是为了在dclThreadSafeSingleton为null的情况下才进行创建实例
                if (dclThreadSafeSingleton == null) {
                    //该句的执行并不是原子操作，所以可能会出现指令重排的情况，在这种情况下，可能出现一个dclThreadSafeSingleton被赋值，但是构造方法还未执行。此时另外一个线程进来调用时，已经不是null的情况。
                    //所以为了避免这种情况，使用了volatile关键字，防止指令重排
                    dclThreadSafeSingleton = new DCLThreadSafeSingleton();
                }
            }
        }
        return dclThreadSafeSingleton;
    }
}