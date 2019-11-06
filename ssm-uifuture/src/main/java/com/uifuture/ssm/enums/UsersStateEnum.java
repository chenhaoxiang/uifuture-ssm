/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.enums;

/**
 * 用户状态枚举
 *
 * @author chenhx
 * @version UsersStateEnum.java, v 0.1 2019-09-18 00:10 chenhx
 */
public enum UsersStateEnum {
    /**
     * 用户状态枚举
     */
    NORMAL("正常", 1),
    FORBIDDEN("禁用", 0),
    ;

    private String name;
    private Integer value;

    UsersStateEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public static UsersStateEnum getByValue(Integer value) {
        UsersStateEnum[] valueList = UsersStateEnum.values();
        for (UsersStateEnum v : valueList) {
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
