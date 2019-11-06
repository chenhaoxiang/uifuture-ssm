/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.bo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uifuture.ssm.base.BaseQueryBo;
import com.uifuture.ssm.entity.UsersCommentEntity;
import lombok.Data;

/**
 * 评论分页查询
 *
 * @author chenhx
 * @version UsersFocusQueryBo.java, v 0.1 2019-09-20 17:31 chenhx
 */
@Data
public class UsersCommentQueryBo extends BaseQueryBo<UsersCommentEntity> {

    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 资源id
     */
    private Integer resourceId;

    @Override
    public QueryWrapper<UsersCommentEntity> buildQuery() {
        QueryWrapper<UsersCommentEntity> queryWrapper = new QueryWrapper<>();
        addId(queryWrapper);
        if (userId != null) {
            queryWrapper.eq(UsersCommentEntity.USER_ID, userId);

        }
        if (resourceId != null) {
            queryWrapper.eq(UsersCommentEntity.RESOURCE_ID, resourceId);

        }
        addSortQuery(queryWrapper);
        addIncludeDeleted(queryWrapper);
        return queryWrapper;
    }
}
