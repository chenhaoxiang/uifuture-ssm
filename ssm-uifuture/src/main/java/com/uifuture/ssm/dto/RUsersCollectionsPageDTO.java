/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenhx
 * @version RUsersCollectionsPageDTO.java, v 0.1 2019-09-21 01:07 chenhx
 */
@Data
public class RUsersCollectionsPageDTO implements Serializable {

    private static final long serialVersionUID = 4363983778898958899L;
    /**
     * 资源token
     */
    private String token;
    /**
     * 资源标题
     */
    private String title;
    /**
     * 图片资源的封面图片地址
     */
    private String imageUrls;
    /**
     * 访问量
     */
    private Integer visitTimes;
    /**
     * 下载量
     */
    private Integer download;
    /**
     * 收藏数
     */
    private Integer collectNumber;
    /**
     * 描述
     */
    private String describe;
}
