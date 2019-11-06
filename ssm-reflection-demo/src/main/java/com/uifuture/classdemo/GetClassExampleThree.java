/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.classdemo;

/**
 * 通过Class.forName()方法获取。这种方式仅需使用类名，就可以获取该类的Class对象，更有利于扩展。也是在一些开源工具项目中常见的用法。
 *
 * @author chenhx
 * @version GetClassExampleOne.java, v 0.1 2018-07-21 下午 4:34
 */
public class GetClassExampleThree {
    public static void main(String[] args) throws ClassNotFoundException {
        //通过对象
        Class aClass = Class.forName("java.lang.String");
        //输出class的全限定名
        System.out.println(aClass);
    }
}