/**
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.spring.core.bean.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * 注册监听器
 *
 * @author chenhx
 * @version RegisterListener.java, v 0.1 2019-04-19 21:39 chenhx
 */
public class RegisterListener implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof RegisterEvent) {
            RegisterEvent registerEvent = (RegisterEvent) event;
            System.out.println(registerEvent.getUsername());
            System.out.println(registerEvent.getEmail());
        } else {
            System.out.println("其他事件，该事件不在监听范围:" + event);
        }
    }
}