package com.uifuture.ssm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.uifuture.ssm.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户ip表
 * </p>
 *
 * @author chenhx
 * @since 2019-09-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ip")
public class IpEntity extends BaseEntity {

    public static final String USER_ID = "user_id";
    public static final String IP = "ip";
    private static final long serialVersionUID = 1L;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * ip地址
     */
    private String ip;

}
