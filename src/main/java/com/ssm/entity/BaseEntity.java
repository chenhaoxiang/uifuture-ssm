package com.ssm.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 所有实体类的超类
 */
@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = -1549634521453074321L;
    // 相当于java类的身份证。主要用于版本控制
    protected Integer id;  //主键，唯一标识符
    protected Integer createId;  //创建者ID
    protected Integer alterId;  //最后修改者ID
    protected Long createTime; //创建时间
    protected Long lastAlterTime; //最后更新时间
    protected String state; //删除标志 （1 正常， 0 删除）
    protected String description; //描述
}
