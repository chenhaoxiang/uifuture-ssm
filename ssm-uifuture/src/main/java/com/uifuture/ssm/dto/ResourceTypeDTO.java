/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分类
 *
 * @author chenhx
 * @version ResourceTypeDTO.java, v 0.1 2019-09-24 17:10 chenhx
 */
@Data
public class ResourceTypeDTO implements Serializable {
    private Integer id;
    /**
     * 资源类别名称、UI,JQurys、免费专区
     */
    private String name;

    private List<ResourceTypeDTO> resourceTypeDTOS = new ArrayList<>();
}
