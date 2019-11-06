/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.enums;

/**
 * 是否被删的枚举
 *
 * @author chenhx
 * @version DeleteEnum.java, v 0.1 2019-09-18 00:13 chenhx
 */
public enum DeleteEnum {
    /**
     * 是否被删的枚举
     */
    NO_DELETE("未被删除", 0),
    ;

    private String name;
    private Integer value;

    DeleteEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public static DeleteEnum getByValue(Integer value) {
        DeleteEnum[] valueList = DeleteEnum.values();
        for (DeleteEnum v : valueList) {
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
