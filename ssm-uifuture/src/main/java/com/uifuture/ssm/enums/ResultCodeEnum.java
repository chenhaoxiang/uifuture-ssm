/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.enums;

/**
 * 响应码枚举
 *
 * @author chenhx
 * @version ResultCodeEnum.java, v 0.1 2019-09-14 11:40 chenhx
 */
public enum ResultCodeEnum {
    /**
     * 成功
     */
    SUCCESS("SUCCESS", 200),
    /**
     * 失败
     */
    FAIL("FAIL", 9999),
    /**
     * 未认证（签名错误）
     */
    UNAUTHORIZED("UNAUTHORIZED", 9998),
    /**
     * 接口不存在
     */
    NOT_FOUND("NOT_FOUND", 404),
    /**
     * 服务器内部错误
     */
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", 500),
    /**
     * 用户未登录
     */
    USER_NOT_LOGGED("请先登录", 9997),
    /**
     * 参数错误
     */
    PARAMETER_ERROR("参数错误", 9996),
    /**
     * 过于频繁
     */
    ALL_TOO_OFTEN("过于频繁,请稍后再试", 9995),
    STATUS_EXCEPTION("状态异常", 9994),
    NO_PRIVILEGE("无权限", 9993),
    USERNAME_ALREADY_EXISTS("用户名已存在", 9992),
    EMAIL_ALREADY_EXISTS("邮箱已存在", 9991),
    VERIFICATION_CODE_HAS_EXPIRED("请重新注册，验证码已过期", 9990),
    VERIFICATION_CODE_ERROR("验证码错误", 9989),
    INCORRECT_MAILBOX_FORMAT("邮箱格式错误", 9988),
    USER_NAME_FORMAT_ERROR("用户名格式错误,字母开头，限字母与数字，长度在6-32", 9987),
    WRONG_PASSWORD_USERNAME_EMAIL("用户名/邮箱/密码错误", 9986),
    USER_VIOLATIONS_ARE_BANNED("用户违规被禁", 9985),
    THE_USER_HAS_BEEN_DELETED("用户已被删除", 9984),
    BUSINESS_PROCESS_FAILED("业务处理失败", 9983),
    PLEASE_UPLOAD_THE_RESOURCE_FILE_FIRST("请先上传资源文件", 9982),
    DATA_DOES_NOT_EXIST("数据不存在", 9981),
    ;

    private String name;
    private Integer value;

    ResultCodeEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public static ResultCodeEnum getByValue(Integer value) {
        ResultCodeEnum[] valueList = ResultCodeEnum.values();
        for (ResultCodeEnum v : valueList) {
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
