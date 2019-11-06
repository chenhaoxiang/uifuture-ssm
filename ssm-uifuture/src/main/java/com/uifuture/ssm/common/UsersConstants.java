/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.common;

/**
 * 用户常量
 *
 * @author chenhx
 * @version UsersConstants.java, v 0.1 2019-09-16 15:54 chenhx
 */
public class UsersConstants {

    /**
     * 用户登录后，存储在session中的用户信息key
     */
    public static final String SESSION_USERS_LOGIN_INFO = "SESSION_USERS_LOGIN_INFO";

    /**
     * 用户登录后，存储在Cookie中的用户信息key
     */
    public static final String COOKIE_USERS_LOGIN_INFO = "COOKIE_USERS_LOGIN_INFO";

    /**
     * 30天，单位秒
     */
    public static final Integer EXPIRATION_DATE_30 = 30 * 24 * 60 * 60;
    /**
     * 用户上传资源次数
     */
    public static final Integer UPLOAD_TIMES = 100;
    /**
     * 限制资源文件只能是ZIP压缩文件
     */
    public static final String UPLOAD_SUFFIX = ".zip";
}
