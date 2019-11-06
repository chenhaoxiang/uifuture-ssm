/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.classloader;

/**
 * 用来演示类加载器的顺序
 *
 * @author chenhx
 * @version TestClassLoader.java, v 0.1 2018-07-17 下午 9:00
 */
public class ClassLoaderTest {
    public static void main(String[] args) {
        ClassLoader loader = ClassLoaderTest.class.getClassLoader();
        while (loader != null) {
            System.err.println(loader);
            loader = loader.getParent();
        }
    }
}
