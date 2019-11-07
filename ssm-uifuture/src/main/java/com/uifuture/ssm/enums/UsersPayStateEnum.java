/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.enums;

/**
 * 显示状态 0待审核 1通过 2驳回 3已扫码
 *
 * @author chenhx
 * @version UsersPayStateEnum.java, v 0.1 2019-11-07 19:51 chenhx
 */
public enum UsersPayStateEnum {
    /**
     *
     */
    TO_BE_AUDITED("待审核", 0),
    ADOPT("通过", 1),
    REJECT("驳回", 2),
    SWEEP_CODE("已扫码", 3),
    ;

    private String name;
    private Integer value;

    UsersPayStateEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public static UsersPayStateEnum getByValue(Integer value) {
        UsersPayStateEnum[] valueList = UsersPayStateEnum.values();
        for (UsersPayStateEnum v : valueList) {
            if (v.getValue().equals(value)) {
                return v;
            }
        }
        return null;
    }

    /**
     * Getter method for property <tt>name</tt>.
     *
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for property <tt>value</tt>.
     *
     * @return property value of value
     */
    public Integer getValue() {
        return value;
    }
}
