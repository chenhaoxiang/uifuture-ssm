/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.enums;

/**
 * @author chenhx
 * @version UsersTypeEnum.java, v 0.1 2019-11-07 16:45 chenhx
 */
public enum UsersTypeEnum {
    /**
     *
     */
    USERS("用户", 0),
    ADMIN("管理员", 1),
    ;

    private String name;
    private Integer value;

    UsersTypeEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public static UsersTypeEnum getByValue(Integer value) {
        UsersTypeEnum[] valueList = UsersTypeEnum.values();
        for (UsersTypeEnum v : valueList) {
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
