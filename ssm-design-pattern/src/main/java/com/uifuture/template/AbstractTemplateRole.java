/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.template;

/**
 * 抽象模板角色
 * 现在我们模拟的是喝茶的场景
 *
 * @author chenhx
 * @version AbstractTemplateRole.java, v 0.1 2018-08-01 下午 9:46
 */
public abstract class AbstractTemplateRole {

    /**
     * 基本抽象方法，由子类实现
     * 烧水可能有柴火烧水，电烧水等等
     */
    protected abstract void boil();

    /**
     * 基本抽象方法，由子类实现
     * 进行泡茶
     * 可能用圆杯子泡红茶，方杯子泡绿茶等等
     */
    protected abstract void makeTea();

    /**
     * 基本方法，空方法，子类可以进行选择是否覆盖实现
     * 默认的钩子方法
     * 配料的选择，因为不是必须的
     */
    protected void burdening() {
    }

    ;

    /**
     * 模板方法 进行喝茶操作
     */
    public final void drinkTea() {
        //按照顺序调用基本方法
        System.out.println("开始泡茶");
        boil();
        makeTea();
        burdening();
        System.out.println("可以喝茶了");
    }
}
