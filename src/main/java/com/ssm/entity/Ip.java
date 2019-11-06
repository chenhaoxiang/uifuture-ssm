package com.ssm.entity;

import lombok.Data;

/**
 * ip
 */
@Data
public class Ip extends BaseEntity {
    //用户Id
    private Integer userId;
    //ip地址
    private String ip;
    //登录时间
    private long loginTime;

    public Ip() {
    }

    public Ip(Integer userId, String ip, long loginTime) {
        this.userId = userId;
        this.ip = ip;
        this.loginTime = loginTime;
    }
}