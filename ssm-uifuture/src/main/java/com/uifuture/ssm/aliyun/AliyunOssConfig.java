/**
 * copyfuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.aliyun;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 阿里云OSS配置
 *
 * @author chenhx
 * @version AliyunOssConfig.java, v 0.1 2019-03-23 04:45 chenhx
 */
@Configuration
@Data
public class AliyunOssConfig {
    /**
     * 阿里云API的内或外网域名
     */
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;
    /**
     * 阿里云API的密钥Access Key ID
     */
    @Value("${aliyun.oss.access.key.id}")
    private String accessKeyId;
    /**
     * 阿里云API的密钥Access Key Secret
     */
    @Value("${aliyun.oss.access.key.secret}")
    private String accessKeySecret;
    /**
     * 阿里云API的bucket名称
     */
    @Value("${aliyun.oss.bucket.img.name}")
    private String bucketImgName;

}
