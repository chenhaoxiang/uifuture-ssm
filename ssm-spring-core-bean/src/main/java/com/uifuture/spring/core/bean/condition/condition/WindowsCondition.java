/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.spring.core.bean.condition.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Objects;

/**
 * @author chenhx
 * @version WindowsCondition.java, v 0.1 2019-03-21 20:54 chenhx
 */
public class WindowsCondition implements Condition {
    /**
     * @param context  判断条件能使用的上下文环境
     * @param metadata 注解所在位置的注释信息
     * @return
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //获取当前环境信息
        Environment environment = context.getEnvironment();
        //获得当前系统名
        String property = environment.getProperty("os.name");
        //包含Windows则说明是windows系统，返回true
        if (Objects.requireNonNull(property).contains("Windows")) {
            return true;
        }
        return false;
    }
}