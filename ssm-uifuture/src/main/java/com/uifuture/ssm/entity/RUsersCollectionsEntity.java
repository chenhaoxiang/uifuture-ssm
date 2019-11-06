package com.uifuture.ssm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.uifuture.ssm.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户收藏资源相关表。
 * </p>
 *
 * @author chenhx
 * @since 2019-09-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("r_users_collections")
public class RUsersCollectionsEntity extends BaseEntity {

    public static final String USER_ID = "user_id";
    public static final String RESOURCE_ID = "resource_id";
    private static final long serialVersionUID = 1L;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 资源id
     */
    private Integer resourceId;

}
