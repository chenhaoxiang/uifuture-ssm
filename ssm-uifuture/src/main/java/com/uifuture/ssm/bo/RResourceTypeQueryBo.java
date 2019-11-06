/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.bo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uifuture.ssm.base.BaseQueryBo;
import com.uifuture.ssm.entity.RResourceTypeEntity;
import lombok.Data;

/**
 * @author chenhx
 * @version UsersFocusQueryBo.java, v 0.1 2019-09-20 17:31 chenhx
 */
@Data
public class RResourceTypeQueryBo extends BaseQueryBo<RResourceTypeEntity> {
    /**
     * 资源id
     */
    private Integer resourceId;
    /**
     * 资源分类id
     */
    private Integer resourceTypeId;

    @Override
    public QueryWrapper<RResourceTypeEntity> buildQuery() {
        QueryWrapper<RResourceTypeEntity> queryWrapper = new QueryWrapper<>();
        addId(queryWrapper);
        if (resourceId != null) {
            queryWrapper.eq(RResourceTypeEntity.RESOURCE_ID, resourceId);

        }
        if (resourceTypeId != null) {
            queryWrapper.eq(RResourceTypeEntity.RESOURCE_TYPE_ID, resourceTypeId);
        }
        addSortQuery(queryWrapper);
        addIncludeDeleted(queryWrapper);
        return queryWrapper;
    }
}
