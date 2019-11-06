/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenhx
 * @version UsersFocusPageDTO.java, v 0.1 2019-09-20 17:56 chenhx
 */
@Data
public class UsersFocusPageDTO implements Serializable {

    private static final long serialVersionUID = 714752209570514626L;


    /**
     * 用户主页图片地址-默认值为默认图片的地址
     */
    private String headImage;

    /**
     * 用户名(唯一而且不能有中文)-用户可以在注册后修改，修改一次90U币
     */
    private String username;

}
