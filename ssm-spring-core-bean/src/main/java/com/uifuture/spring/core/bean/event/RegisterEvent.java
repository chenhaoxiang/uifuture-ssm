/**
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.spring.core.bean.event;

import org.springframework.context.ApplicationEvent;

/**
 * 注册事件
 *
 * @author chenhx
 * @version RegisterEvent.java, v 0.1 2019-04-19 21:31 chenhx
 */
public class RegisterEvent extends ApplicationEvent {
    /**
     * 注册名称
     */
    private String username;
    /**
     * 注册邮件
     */
    private String email;

    public RegisterEvent(Object source, String username, String email) {
        super(source);
        this.username = username;
        this.email = email;
    }

    public RegisterEvent(Object source) {
        super(source);
    }

    /**
     * Getter method for property <tt>username</tt>.
     *
     * @return property value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter method for property <tt>username</tt>.
     *
     * @param username value to be assigned to property username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter method for property <tt>email</tt>.
     *
     * @return property value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter method for property <tt>email</tt>.
     *
     * @param email value to be assigned to property email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}