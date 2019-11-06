/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.bo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uifuture.ssm.base.BaseQueryBo;
import com.uifuture.ssm.entity.RResourceSubjectEntity;
import lombok.Data;

/**
 * 专题资源分页数据条件构造器
 * @author chenhx
 * @version UsersFocusQueryBo.java, v 0.1 2019-09-20 17:31 chenhx
 */
@Data
public class RResourceSubjectQueryBo extends BaseQueryBo<RResourceSubjectEntity> {
    /**
     * 资源表id
     */
    private Integer resourceId;
    /**
     * 专题表id
     */
    private Integer subjectId;

    @Override
    public QueryWrapper<RResourceSubjectEntity> buildQuery() {
        QueryWrapper<RResourceSubjectEntity> queryWrapper = new QueryWrapper<>();
        addId(queryWrapper);
        if (resourceId != null) {
            //构造资源id的查询条件
            queryWrapper.eq(RResourceSubjectEntity.RESOURCE_ID, resourceId);

        }
        if (subjectId != null) {
            //构造专题id的查询条件
            queryWrapper.eq(RResourceSubjectEntity.SUBJECT_ID, subjectId);

        }
        //添加排序条件
        addSortQuery(queryWrapper);
        //是否包含删除的数据
        addIncludeDeleted(queryWrapper);
        return queryWrapper;
    }
}
