/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.classdemo;

import lombok.Data;

import java.util.Date;

/**
 * 测试用的实体类
 *
 * @author chenhx
 * @version Users.java, v 0.1 2018-07-23 下午 9:23
 */
@Data
public class Users {
    private String name;
    private Integer age;
    private String address;
    private Date createTime;
}