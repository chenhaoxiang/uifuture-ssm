/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.enums;

/**
 * @author chenhx
 * @version PayTypeEnNameEnum.java, v 0.1 2019-11-06 20:59 chenhx
 */
public enum PayTypeEnNameEnum {
    /**
     *
     */
    ALI_APY("支付宝","ALI_PAY"),
    ;

    private String name;
    private String value;

    PayTypeEnNameEnum(String name, String value) {
        this.name = name;
        this.value = value;
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

    public static PayTypeEnNameEnum getByValue(String value) {
        PayTypeEnNameEnum[] valueList = PayTypeEnNameEnum.values();
        for (PayTypeEnNameEnum v : valueList) {
            if (v.getValue().equals(value)) {
                return v;
            }
        }
        return null;
    }
}
