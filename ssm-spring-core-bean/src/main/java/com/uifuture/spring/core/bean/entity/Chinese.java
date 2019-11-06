/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.spring.core.bean.entity;

/**
 * @author chenhx
 * @version Chinese.java, v 0.1 2019-02-16 15:26 chenhx
 */
//@Configuration
//@Scope(scopeName = "singleton")
public class Chinese {
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public void getMessage() {
        System.out.println("Message : " + message);
    }
}