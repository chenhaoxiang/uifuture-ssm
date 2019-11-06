/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.helloword;

import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Arrays;

/**
 * 类反射的Helloworld版---入门
 * 只演示了类方法的类反射---Method[]
 *
 * @author chenhx
 * @version RedlectionHelloWord.java, v 0.1 2018-07-19 下午 9:26
 */
public class ReflectionHelloWord {
    public static void main(String[] args) {
        try {
            Class c = Class.forName("com.uifuture.helloword.entity.Person");
            System.out.println(MessageFormat.format("类:{0}", c.toString()));
            Method[] methods = c.getDeclaredMethods();
            for (Method m : methods) {
                //获得整个方法
                //包括修饰，返回类型，方法名字，方法参数
                System.out.println(MessageFormat.format("方法名:{0}", m.toString()));
                System.out.println(MessageFormat.format("修饰符:{0}", m.getModifiers()));
                System.out.println(MessageFormat.format("返回类型:{0}"
                        , m.getReturnType()));
                System.out.println(MessageFormat.format("方法名字:{0}", m.getName()));
                Class[] classes = m.getParameterTypes();
                //此处就是遍历classes数组
                Arrays.asList(classes).stream().forEach(x ->
                        System.out.println(MessageFormat.format("参数:{0}", x)));
                System.out.println(MessageFormat.format("方法所在的类:{0}"
                        , m.getDeclaringClass()));
                System.out.println("------------------------------");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}