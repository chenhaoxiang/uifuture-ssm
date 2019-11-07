/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenhx
 * @version PageDTO.java, v 0.1 2019-11-07 16:56 chenhx
 */
@Data
public class PageDTO implements Serializable {
    private static final long serialVersionUID = -8375628539328092637L;
    /**
     * 当前页
     */
    private Integer currentIndex = 1;

    /**
     * 每页记录数
     */
    private Integer pageSize = 20;


}
