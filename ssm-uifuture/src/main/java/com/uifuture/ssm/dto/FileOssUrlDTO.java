/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 图片上传到OSS返回信息
 *
 * @author chenhx
 * @version FileOssUrlDTO.java, v 0.1 2019-09-19 00:37 chenhx
 */
@Data
public class FileOssUrlDTO implements Serializable {

    private static final long serialVersionUID = 4891476444313164521L;
    /**
     * 文件名
     */
    private String fileName;

    /**
     * oss URL
     */
    private String ossUrl;
}
