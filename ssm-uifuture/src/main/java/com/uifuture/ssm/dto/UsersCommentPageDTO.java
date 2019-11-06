package com.uifuture.ssm.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户评论表
 * </p>
 *
 * @author chenhx
 * @since 2019-09-21
 */
@Data
public class UsersCommentPageDTO implements Serializable {
    private static final long serialVersionUID = 6062593546873890726L;
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
     * 评论的用户名
     */
    private String username;

    /**
     * 创建时间
     */
    private Date createTime;
}
