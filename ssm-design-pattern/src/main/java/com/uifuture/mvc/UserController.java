/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.mvc;

/**
 * 控制器
 *
 * @author chenhx
 * @version UserController.java, v 0.1 2018-08-02 下午 10:12
 */
public class UserController {
    private User user;
    private UserView userView;

    public UserController(User user, UserView userView) {
        this.user = user;
        this.userView = userView;
    }

    /**
     * 更新模型数据
     */
    public void setUserPassword(String password) {
        user.setPassword(password);
    }

    /**
     * 进行更新视图
     */
    public void updateView() {
        userView.show(user);
    }
}