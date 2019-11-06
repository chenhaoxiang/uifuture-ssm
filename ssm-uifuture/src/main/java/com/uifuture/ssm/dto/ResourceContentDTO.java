/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenhx
 * @version ResourceContentDTO.java, v 0.1 2019-09-18 18:06 chenhx
 */
@Data
public class ResourceContentDTO implements Serializable {
    private static final long serialVersionUID = 4507919213311807189L;

    /**
     * 资源描述与内容
     */
    private String content;
    /**
     * 资源token
     */
    private String resourceToken;


}
