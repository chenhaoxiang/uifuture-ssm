/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.template;

import com.uifuture.template.impl.BlackTeaImpl;
import com.uifuture.template.impl.GreenTeaImpl;

/**
 * 测试客户端类
 *
 * @author chenhx
 * @version TestClient.java, v 0.1 2018-08-01 下午 10:11
 */
public class TestClient {
    public static void main(String[] args) {
        AbstractTemplateRole templateRole = new GreenTeaImpl();
        templateRole.drinkTea();
        System.out.println("============");
        //接下来进行调用另外一种方式
        templateRole = new BlackTeaImpl();
        templateRole.drinkTea();
    }
}