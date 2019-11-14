/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.aliyun.model;

import com.uifuture.ssm.enums.MqTypeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author chenhx
 * @version UbBodyModel.java, v 0.1 2019-11-14 17:46 chenhx
 */
@Data
public class UbBodyModel<T> implements Serializable {
    private static final long serialVersionUID = 7643613014199376375L;

    /**
     * 类型
     */
    private MqTypeEnum mqTypeEnum;

    private T data;
}
