/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.bo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uifuture.ssm.base.BaseQueryBo;
import com.uifuture.ssm.entity.UsersFocusEntity;
import lombok.Data;

/**
 * @author chenhx
 * @version UsersFocusQueryBo.java, v 0.1 2019-09-20 17:31 chenhx
 */
@Data
public class UsersFocusQueryBo extends BaseQueryBo<UsersFocusEntity> {

    /**
     * 用户id-关注者id
     */
    private Integer userId;
    /**
     * 被关注者的id
     */
    private Integer focusedUserId;

    @Override
    public QueryWrapper<UsersFocusEntity> buildQuery() {
        QueryWrapper<UsersFocusEntity> queryWrapper = new QueryWrapper<>();
        addId(queryWrapper);
        if (userId != null) {
            queryWrapper.eq(UsersFocusEntity.USER_ID, userId);
        }
        if (focusedUserId != null) {
            queryWrapper.eq(UsersFocusEntity.FOCUSED_USER_ID, focusedUserId);

        }
        addSortQuery(queryWrapper);
        addIncludeDeleted(queryWrapper);
        return queryWrapper;
    }
}
