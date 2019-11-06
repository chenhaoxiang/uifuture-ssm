/**
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.spring.core.bean.entity;

import com.uifuture.spring.core.bean.event.RegisterEvent;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring中的事件测试
 *
 * @author chenhx
 * @version EventTest.java, v 0.1 2019-04-19 21:47 chenhx
 */
public class EventTest {
    /**
     * 测试触发事件
     */
    @Test
    public void registerEventTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("event-spring-content.xml");
        //创建一个ApplicationEvent对象
        RegisterEvent registerEvent = new RegisterEvent("任何对象", "ssm", "abc@**.com");
        //主动触发事件
        context.publishEvent(registerEvent);
        ((ClassPathXmlApplicationContext) context).stop();
        ((ClassPathXmlApplicationContext) context).close();
    }
}