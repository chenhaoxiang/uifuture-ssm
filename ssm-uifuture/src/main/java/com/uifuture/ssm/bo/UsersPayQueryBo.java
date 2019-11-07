/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.bo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uifuture.ssm.base.BaseQueryBo;
import com.uifuture.ssm.entity.UsersPayEntity;
import lombok.Data;

/**
 * @author chenhx
 * @version UsersPayQueryBo.java, v 0.1 2019-11-07 17:31 chenhx
 */
@Data
public class UsersPayQueryBo extends BaseQueryBo<UsersPayEntity> {

    /**
     * 用户id
     */
    private Integer usersId;
    /**
     * 订单号
     */
    private String orderNumber;


    @Override
    public QueryWrapper<UsersPayEntity> buildQuery() {
        QueryWrapper<UsersPayEntity> queryWrapper = new QueryWrapper<>();
        addId(queryWrapper);
        if (usersId != null) {
            queryWrapper.eq(UsersPayEntity.USERS_ID, usersId);
        }
        if (orderNumber != null) {
            queryWrapper.eq(UsersPayEntity.ORDER_NUMBER, orderNumber);

        }
        addSortQuery(queryWrapper);
        addIncludeDeleted(queryWrapper);
        return queryWrapper;
    }
}
