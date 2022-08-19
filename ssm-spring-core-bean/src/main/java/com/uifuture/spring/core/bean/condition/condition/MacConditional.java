/*
 * chenhx
 * Copyright (C) 2013-2022 All Rights Reserved.
 */
package com.uifuture.spring.core.bean.condition.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Objects;

/**
 * @author chenhx
 * @version 0.0.1
 * @className MacConditional.java
 * @date 2022-08-20 03:20
 * @description mac环境判断
 */
public class MacConditional implements Condition {
    /**
     * @param context  判断条件能使用的上下文环境
     * @param metadata 注解所在位置的注释信息
     * @return
     */
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //获取运行环境信息
        Environment environment = context.getEnvironment();
        String property = environment.getProperty("os.name");
        //如果在Mac系统下,返回true,即满足条件
        if (Objects.requireNonNull(property).contains("Mac")) {
            return true;
        }
        return false;
    }

}
