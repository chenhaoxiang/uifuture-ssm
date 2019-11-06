/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.req;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author chenhx
 * @version UsersPayReq.java, v 0.1 2019-11-06 20:06 chenhx
 */
@Data
public class UsersPayReq implements Serializable {
   private static final long serialVersionUID = 97540252102280165L;
   /**
    * 金额
    */
   @NotNull(message = "金额不能为空")
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
    * 支付标识
    */
   private String payNum;

   /**
    * 是否自定义输入，0否
    */
   private Integer custom=1;

   /**
    * 是否移动端，0否
    */
   private Integer mobile;

   /**
    * 用户支付设备信息
    */
   private String device;


}
