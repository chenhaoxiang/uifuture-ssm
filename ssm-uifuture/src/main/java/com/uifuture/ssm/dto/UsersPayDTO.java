/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author chenhx
 * @version UsersPayDTO.java, v 0.1 2019-11-07 19:25 chenhx
 */
@Data
public class UsersPayDTO implements Serializable {

    /**
     * 金额
     */
    private BigDecimal money;

    /**
     * 留言
     */
    private String info;

    /**
     * 通知邮箱
     */
    private String email;

    /**
     * 显示状态 0待审核 1通过 2驳回 3已扫码
     */
    private Integer state;

    /**
     * 状态描述
     */
    private String stateStr;

    /**
     * pay_type的en_name，支付类型
     */
    private String payTypeEnName;

    /**
     * 支付标识
     */
    private String payNum;

    /**
     * 是否自定义输入，0否
     */
    private Integer custom;

    /**
     * 是否移动端，0否
     */
    private Integer mobile;

    /**
     * 用户支付设备信息
     */
    private String device;

    /**
     * 生成二维码编号标识token
     */
    private String tokenNum;

    /**
     * 通过审核的url
     */
    private String passUrl;

    /**
     * 操作后的url
     */
    private String backUrl;

    /**
     * 关闭交易的url
     */
    private String closeUrl;

    /**
     * 订单号
     */
    private String orderNumber;


    /**
     * 用户id
     */
    private Integer usersId;

}
