/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 资源文件上传后返回信息
 * @author chenhx
 * @version FileInfoDTO.java, v 0.1 2019-09-18 16:42 chenhx
 */
@Data
public class FileInfoDTO implements Serializable {

    private static final long serialVersionUID = -3706119957109881115L;

    /**
     * 新的文件名称
     */
    private String newFileName;
    /**
     * 原来的文件名称
     */
    private String oldFileName;
    /**
     * 文件存储路径
     */
    private String path;
}
