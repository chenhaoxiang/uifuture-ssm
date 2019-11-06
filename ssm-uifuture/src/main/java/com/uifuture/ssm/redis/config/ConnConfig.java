package com.uifuture.ssm.redis.config;

import lombok.Data;

@Data
public class ConnConfig {
    /**
     * 地址
     */
    private String host;

    /**
     * 端口
     */
    private Integer port;

    /**
     * 密码
     */
    private String password;

    /**
     * 项目名称
     */
    private String app;

}
