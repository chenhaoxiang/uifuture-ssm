/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.chapter17.domain.base;

import lombok.Data;

import java.util.Date;

/**
 * 数据库实体基类
 *
 * @author chenhx
 * @version BaseEntity.java, v 0.1 2019-08-23 00:12 chenhx
 */
@Data
public class BaseEntity {
    public static final String ID = "id";
    public static final String CREATE_TIME = "create_time";
    public static final String UPDATE_TIME = "update_time";
    public static final String DELETE_TIME = "delete_time";
    /**
     * id
     */
    private Integer id;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 删除时间
     */
    private Long deleteTime;
}
