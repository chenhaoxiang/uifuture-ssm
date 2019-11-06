package com.uifuture.ssm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.uifuture.ssm.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户关注表
 * </p>
 *
 * @author chenhx
 * @since 2019-09-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("users_focus")
public class UsersFocusEntity extends BaseEntity {

    public static final String USER_ID = "user_id";
    public static final String FOCUSED_USER_ID = "focused_user_id";
    private static final long serialVersionUID = 1L;
    /**
     * 用户id-关注者id
     */
    private Integer userId;
    /**
     * 被关注者的id
     */
    private Integer focusedUserId;

}
