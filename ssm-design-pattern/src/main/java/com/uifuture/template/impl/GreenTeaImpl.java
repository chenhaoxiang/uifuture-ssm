/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.template.impl;

import com.uifuture.template.AbstractTemplateRole;

/**
 * 具体模板实现类
 *
 * @author chenhx
 * @version GreenTeaImpl.java, v 0.1 2018-08-01 下午 10:05
 */
public class GreenTeaImpl extends AbstractTemplateRole {
    /**
     * 进行选择烧火方式
     */
    @Override
    protected void boil() {
        System.out.println("使用柴火烧水...");
    }

    /**
     * 选择茶叶和杯子
     */
    @Override
    protected void makeTea() {
        System.out.println("使用圆杯泡绿茶");
    }
}