/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.spring.aop.xml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author chenhx
 * @version PrintLog.java, v 0.1 2019-06-27 19:52 chenhx
 */
public class PrintLog {

    /**
     * 方法执行后
     *
     * @param jp
     */
    public void doAfter(JoinPoint jp) {
        System.out.println(jp.getTarget().getClass().getName() + "." + jp.getSignature().getName() + "方法执行完毕");
    }

    /**
     * 环绕方法
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long time = System.currentTimeMillis();
        Object retVal = pjp.proceed();
        time = System.currentTimeMillis() - time;
        System.out.println(pjp.getTarget().getClass().getName() + "." + pjp.getSignature().getName() + "方法执行时间: " + time + " ms");
        System.out.println("返回参数:" + retVal);
        return retVal;
    }

    /**
     * 方法执行前
     *
     * @param jp
     */
    public void doBefore(JoinPoint jp) {
        System.out.println(jp.getTarget().getClass().getName() + "." + jp.getSignature().getName() + "方法即将执行");
        StringBuilder stringBuilder = new StringBuilder();
        Object[] objects = jp.getArgs();
        for (int i = 0; i < objects.length; i++) {
            stringBuilder.append("参数").append(i).append(":").append(objects[i]).append("\n");
        }
        System.out.println("入参:" + stringBuilder);
    }

    /**
     * 抛出异常执行
     *
     * @param jp
     * @param ex
     */
    public void doThrowing(JoinPoint jp, Throwable ex) {
        System.out.println(jp.getTarget().getClass().getName() + "." + jp.getSignature().getName() + "方法抛出异常");
        System.out.println(ex.getMessage());
    }

}
