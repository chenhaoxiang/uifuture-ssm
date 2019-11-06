package com.uifuture.ssm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.uifuture.ssm.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户评论表
 * </p>
 *
 * @author chenhx
 * @since 2019-09-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("users_comment")
public class UsersCommentEntity extends BaseEntity {

    public static final String USER_ID = "user_id";
    public static final String RESOURCE_ID = "resource_id";
    public static final String PID = "pid";
    public static final String DETAILS = "details";
    public static final String STATE = "state";
    public static final String REAL_DETAILS = "real_details";
    private static final long serialVersionUID = 1L;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 资源id
     */
    private Integer resourceId;
    /**
     * 父类的评论id
     */
    private Integer pid;
    /**
     * 评论内容-过滤敏感词之后的
     */
    private String details;
    /**
     * 评论的状态 1-正常 0-逻辑上的删除
     */
    private Integer state;
    /**
     * 实际的评论内容
     */
    private String realDetails;

}
