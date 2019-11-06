/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.classdemo;

/**
 * 第一种方式：通过对象的getClass方法进行获取。这种方式需要具体的类和该类的对象，以及调用getClass方法。
 *
 * @author chenhx
 * @version GetClassExampleOne.java, v 0.1 2018-07-21 下午 4:34
 */
public class GetClassExampleOne {
    public static void main(String[] args) {
        //通过对象
        Class aClass = new String("test").getClass();
        //输出class的全限定名
        System.out.println(aClass);
    }
}