/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.enums;

/**
 * 资源状态枚举
 * 状态：0-审核未通过 1-审核已通过 2-审核中 3-资源被封
 *
 * @author chenhx
 * @version ResourceStateEnum.java, v 0.1 2019-09-18 10:00 chenhx
 */
public enum ResourceStateEnum {
    AUDIT_FAILED("审核未通过", 0),
    APPROVED_BY_THE_AUDIT("审核已通过", 1),
    IN_THE_REVIEW("审核中", 2),
    RESOURCES_ARE_SEALED("资源被封", 3),
    ;

    private String name;
    private Integer value;

    ResourceStateEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public static ResourceStateEnum getByValue(Integer value) {
        ResourceStateEnum[] valueList = ResourceStateEnum.values();
        for (ResourceStateEnum v : valueList) {
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
