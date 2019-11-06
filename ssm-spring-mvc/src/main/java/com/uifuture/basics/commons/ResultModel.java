/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.basics.commons;

/**
 * 返回结果
 *
 * @author chenhx
 * @version ResultModel.java, v 0.1 2018-08-22 下午 9:18
 */
public class ResultModel {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 消息
     */
    private String message;

    public ResultModel(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 返回失败,使用默认状态码
     *
     * @param message
     * @return
     */
    public static ResultModel error(String message) {
        return ResultModel.error(ResultCodeEnum.ERROR.getCode(), message);
    }

    /**
     * 返回失败
     *
     * @param message
     * @return
     */
    public static ResultModel error(Integer code, String message) {
        return new ResultModel(code, message);
    }

    /**
     * 返回成功,使用默认状态码
     *
     * @param message
     * @return
     */
    public static ResultModel success(String message) {
        return ResultModel.success(ResultCodeEnum.SUCCESS.getCode(), message);
    }

    /**
     * 返回成功
     *
     * @param code
     * @param message
     * @return
     */
    public static ResultModel success(Integer code, String message) {
        return new ResultModel(code, message);
    }

    /**
     * Getter method for property <tt>code</tt>.
     *
     * @return property value of code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * Setter method for property <tt>code</tt>.
     *
     * @param code value to be assigned to property code
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * Getter method for property <tt>message</tt>.
     *
     * @return property value of message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Setter method for property <tt>message</tt>.
     *
     * @param message value to be assigned to property message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ResultModel{");
        sb.append("code=").append(code);
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}