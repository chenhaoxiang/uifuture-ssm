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
 * @version LinuxCondition.java, v 0.1 2019-03-21 20:54 chenhx
 */
public class LinuxCondition implements Condition {
    /**
     * @param context  判断条件能使用的上下文环境
     * @param metadata 注解所在位置的注释信息
     * @return
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        String property = environment.getProperty("os.name");
        if (Objects.requireNonNull(property).contains("Linux")) {
            return true;
        }
        return false;
    }
}