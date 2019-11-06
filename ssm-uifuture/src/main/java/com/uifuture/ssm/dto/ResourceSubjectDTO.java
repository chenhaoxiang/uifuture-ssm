/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenhx
 * @version ResourceSubjectDTO.java, v 0.1 2019-09-25 00:42 chenhx
 */
@Data
public class ResourceSubjectDTO implements Serializable {
    private Integer id;
    /**
     * 专题名
     */
    private String name;
    /**
     * 专题封面图片地址
     */
    private String imageUrl;
    /**
     * 专题描述
     */
    private String description;
}
