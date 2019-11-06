/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 存储在客户端的用户信息
 *
 * @author chenhx
 * @version UsersCookieDTO.java, v 0.1 2019-09-17 15:03 chenhx
 */
@Data
public class UsersCookieDTO implements Serializable {
    private static final long serialVersionUID = -1567047698256969332L;
    /**
     * 用户名
     */
    private String username;
    /**
     * 加密规则看代码
     */
    private String token;

    /**
     * 毫秒
     */
    private Long time;
}
