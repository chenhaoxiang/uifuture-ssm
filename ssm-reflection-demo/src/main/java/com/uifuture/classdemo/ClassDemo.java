/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.classdemo;

import com.uifuture.helloword.entity.Person;

/**
 * 演示new同一个类，类对象的class也是相同的
 *
 * @author chenhx
 * @version ClassDemo.java, v 0.1 2018-07-21 下午 5:28
 */
public class ClassDemo {
    public static void main(String[] args) throws ClassNotFoundException {
        Person person = new Person();
        Person person1 = new Person();
        System.out.println(person == person1);
        System.out.println(person.getClass() == person1.getClass());
        System.out.println(Person.class == Person.class);
        System.out.println(Class.forName("com.uifuture.helloword.entity.Person") ==
                Class.forName("com.uifuture.helloword.entity.Person"));
    }
}