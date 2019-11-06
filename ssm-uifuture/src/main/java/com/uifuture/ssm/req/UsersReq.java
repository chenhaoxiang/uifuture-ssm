/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.req;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 用户注册实体
 *
 * @author chenhx
 * @version UsersReq.java, v 0.1 2019-09-14 11:34 chenhx
 */
@Data
public class UsersReq implements Serializable {
    /**
     * 用户名
     */
    @NotEmpty(message = "用户名不能为空")
    @Length(min = 6, max = 32, message = "用户名长度应在6-32之间")
    private String username;
    /**
     * 密码(使用MD5+盐加密)
     */
    @NotEmpty(message = "密码不能为空")
    @Length(min = 6, max = 32, message = "密码长度应在6-32之间")
    private String password;
    /**
     * 邮箱
     */
    @NotEmpty(message = "邮箱不能为空")
    @Length(max = 100, message = "邮箱最长为100位")
    private String email;
    /**
     * 验证码
     */
    @NotEmpty(message = "邮箱验证码不能为空")
    @Length(min = 6, max = 6, message = "邮箱验证码为6位数字")
    private String emailCode;

    /**
     * 是否自动登录
     */
    private Boolean rememberMe = false;
}
