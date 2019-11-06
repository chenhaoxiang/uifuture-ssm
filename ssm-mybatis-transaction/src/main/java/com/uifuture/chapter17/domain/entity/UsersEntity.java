package com.uifuture.chapter17.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.uifuture.chapter17.domain.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author chenhx
 * @since 2019-08-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("users")
public class UsersEntity extends BaseEntity {

    public static final String USERNAME = "username";
    public static final String MONEY = "money";
    private static final long serialVersionUID = 1L;
    /**
     * 用户名
     */
    private String username;
    /**
     * 金额，单位：分
     */
    private Integer money;

}
