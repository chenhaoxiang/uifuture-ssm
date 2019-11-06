/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 资源信息
 *
 * @author chenhx
 * @version ResourceDTO.java, v 0.1 2019-09-19 10:52 chenhx
 */
@Data
public class ResourceDTO implements Serializable {

    private static final long serialVersionUID = 482597943530803186L;
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
     * U币价格(只有整数)
     */
    private Integer priceUb;
    /**
     * 人民币价格,单位分
     */
    private Integer price;
    /**
     * 收藏数
     */
    private Integer collectNumber;
    /**
     * 描述
     */
    private String describe;

    /**
     * 标签
     */
    private List<TagsDTO> tagsDTOS = new ArrayList<>();

    /**
     * 创建时间
     */
    private Date createTime;
}
