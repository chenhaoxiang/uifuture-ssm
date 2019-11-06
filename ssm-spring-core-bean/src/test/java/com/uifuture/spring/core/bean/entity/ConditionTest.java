/**
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.spring.core.bean.entity;

import com.uifuture.spring.core.bean.condition.config.UserConfig;
import com.uifuture.spring.core.bean.condition.entity.User;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

/**
 * 演示Condition接口的使用
 *
 * @author chenhx
 * @version ConditionTest.java, v 0.1 2019-03-21 20:46 chenhx
 */
public class ConditionTest {
    /**
     * 验证下这两个Bean是否已经都成功注入
     */
    @Test
    public void loadUserBeanTest() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(UserConfig.class);
        Map<String, User> map = applicationContext.getBeansOfType(User.class);
        System.out.println(map);
    }
}