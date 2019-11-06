/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.classdemo;

/**
 * 第二种方式：任何数据类都具有着一个静态的属性class，通过该属性直接获取到该类型对应的Class对象。
 * 这种方式需要使用到具体的类，然后调用类中的静态属性class完成，不需要进行调用方法，性能比第一种方式要好一些。
 *
 * @author chenhx
 * @version GetClassExampleOne.java, v 0.1 2018-07-21 下午 4:34
 */
public class GetClassExampleTwo {
    public static void main(String[] args) {
        //通过对象
        Class aClass = String.class;
        //输出class的全限定名
        System.out.println(aClass);
        System.out.println(Integer.TYPE);

    }
}