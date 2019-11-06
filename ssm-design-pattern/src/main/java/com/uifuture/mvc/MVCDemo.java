/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.mvc;

/**
 * MVC设计模式演示
 *
 * @author chenhx
 * @version MVCDemo.java, v 0.1 2018-08-02 下午 10:20
 */
public class MVCDemo {
    public static void main(String[] args) {
        //获取输入的数据
        User user = new User();
        user.setId(1);
        user.setUsername("chenhx");
        user.setPassword("1234");
        //接下来创建视图，将信息输出到控制台
        UserView userView = new UserView();
        //下面是使用控制器，进行数据更新
        UserController userController = new UserController(user, userView);
        userController.updateView();
        //再进行更新模型数据
        userController.setUserPassword("6666");
        //更新后进行模型数据的视图展示
        userController.updateView();
    }
}