/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.basics.commons;

/**
 * 返回状态码枚举
 *
 * @author chenhx
 * @version ResultCodeEnum.java, v 0.1 2018-08-22 下午 9:23
 */
public enum ResultCodeEnum {
    /**
     * 返回成功状态码
     */
    SUCCESS(200),
    /**
     * 失败状态码
     */
    ERROR(-1);
    /**
     * 状态码
     */
    private Integer code;

    ResultCodeEnum(Integer code) {
        this.code = code;
    }

    /**
     * Getter method for property <tt>code</tt>.
     *
     * @return property value of code
     */
    public Integer getCode() {
        return code;
    }
}
