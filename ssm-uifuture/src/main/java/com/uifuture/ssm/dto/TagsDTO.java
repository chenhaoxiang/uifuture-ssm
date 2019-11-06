/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 标签
 *
 * @author chenhx
 * @version TagsDTO.java, v 0.1 2019-09-19 10:55 chenhx
 */
@Data
public class TagsDTO implements Serializable {

    private static final long serialVersionUID = -4277580515071868734L;


    /**
     * 标签名
     */
    private String name;
    /**
     * 属于该标签的资源访问量
     */
    private Integer visitTimes;

    private Integer id;
}
