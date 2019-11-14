/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.enums;

/**
 * 消息队列的消息类型枚举
 *
 * @author chenhx
 * @version MqTypeEnum.java, v 0.1 2019-11-14 16:02 chenhx
 */
public enum MqTypeEnum {
    /**
     * 用户支付成功，但是增加UB，事务失败
     */
    ADD_UB_ERROR("添加UB失败", "ADD_UB_ERROR"),
    ;

    private String name;
    private String value;

    MqTypeEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static MqTypeEnum getByValue(String value) {
        MqTypeEnum[] valueList = MqTypeEnum.values();
        for (MqTypeEnum v : valueList) {
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
    public String getValue() {
        return value;
    }
}
