/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uifuture.ssm.bo.SortQueryBo;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenhx
 * @version BaseQueryBo.java, v 0.1 2019-09-20 17:31 chenhx
 */
@Data
public abstract class BaseQueryBo<T> {
    /**
     * 主键ID
     */
    protected Integer id;

    /**
     * 排序
     */
    protected List<SortQueryBo> sorts;


    /**
     * 是否包含被删除的数据
     * 默认不包含被删除的数据
     */
    protected Boolean includeDeleted = false;

    /**
     * 查询的数据增加排序规则
     *
     * @param query
     */
    protected void addSortQuery(QueryWrapper<?> query) {
        //default id倒序
        if (CollectionUtils.isEmpty(sorts)) {
            sorts = new ArrayList<>();
            SortQueryBo sortQueryBo = new SortQueryBo();
            sortQueryBo.setFieldName(BaseEntity.ID);
            sortQueryBo.setSortTypeEnum(SortQueryBo.SortTypeEnum.DESC);
            sorts.add(sortQueryBo);
        }
        sorts.forEach(sortReq -> {
            if (SortQueryBo.SortTypeEnum.ASC.getCode().equals(sortReq.getSortTypeEnum().getCode())) {
                query.orderByAsc(sortReq.getFieldName());
            } else {
                query.orderByDesc(sortReq.getFieldName());
            }
        });
    }

    /**
     * 是否包含删除的数据
     *
     * @param queryWrapper
     */
    protected void addIncludeDeleted(QueryWrapper<?> queryWrapper) {
        if (!includeDeleted) {
            queryWrapper.eq(BaseEntity.DELETE_TIME, 0);
        }
    }

    /**
     * 根据id查询
     *
     * @param queryWrapper
     */
    protected void addId(QueryWrapper<?> queryWrapper) {
        if (id != null) {
            queryWrapper.eq(BaseEntity.ID, id);
        }
    }

    /**
     * 构造查询条件
     *
     * @return
     */
    protected abstract QueryWrapper<T> buildQuery();
}
