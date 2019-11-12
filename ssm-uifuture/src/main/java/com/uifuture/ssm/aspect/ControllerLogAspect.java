/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.aspect;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * Controller层日志拦截器
 *
 * 想使用AOP 拦截Spring MVC的Controller中的异常防止异常传到页面上, 发现无法拦截Controller的方法, 却可以拦截Service层的方法.
 *
 * 流程跟踪，发现了猫腻，因为Spring的Bean扫描和Spring-MVC的Bean扫描是分开的, 两者的Bean位于两个不同的Application, 而且Spring-MVC的Bean扫描要早于Spring的Bean扫描, 所以当Controller Bean生成完成后, 再执行Spring的Bean扫描,Spring会发现要被AOP代理的Controller Bean已经在容器中存在, 配置AOP就无效了.
 *
 * 同样这样的情况也存在于数据库事务中, 如果Service的Bean扫描配置在spring-mvc.xml中, 而数据库事务管理器配置在application.xml中, 会导致数据库事务失效, 原理一样.
 *
 * 所以这里 ,我们需要把AOP也放置在Controller扫描配置的文件中.
 *
 * （需要spring配置文件包含aop自动代理，spring mvc的配置文件也需要包含，开启controller的代理）
 *
 * @author chenhx
 * @version ServiceLogAspect.java, v 0.1 2019-09-18 18:09 chenhx
 */
@Aspect
@Component
@Slf4j
public class ControllerLogAspect {
    /**
     * 通过@Around注解声明一个建言,此建言直接使用拦截规则作为参数
     * 拦截接口实现
     *
     * @param joinPoint
     */
    @Around("execution(public * com.uifuture.ssm.controller.*.*(..))")
    public Object aroundController(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getName();
        String className = methodSignature.getDeclaringTypeName();
        Object[] args = joinPoint.getArgs();
        Object result = null;
        try {
            log.info("方法名:{}#{}，入参:{}", className, methodName, JSON.toJSONString(args));
            long s = System.currentTimeMillis();
            result = joinPoint.proceed();
            long e = System.currentTimeMillis();
            if (e - s > 1000) {
                log.warn("方法名:{}#{}, 消耗时间:{}ms", className, methodName, (e - s));
            }
        } catch (Exception e) {
            log.error("方法名:" + className + "#" + methodName + "，入参:" + JSON.toJSONString(args) + ",出现异常", e);
            throw e;
        }
        return result;
    }
}
