/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.bo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uifuture.ssm.base.BaseQueryBo;
import com.uifuture.ssm.entity.TagsEntity;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author chenhx
 * @version TagsQueryBo.java, v 0.1 2019-09-25 00:56 chenhx
 */
@Data
public class TagsQueryBo extends BaseQueryBo<TagsEntity> {

    /**
     * 标签名
     */
    private String name;

    @Override
    public QueryWrapper<TagsEntity> buildQuery() {
        QueryWrapper<TagsEntity> queryWrapper = new QueryWrapper<>();
        addId(queryWrapper);
        if (StringUtils.isNotEmpty(name)) {
            //构造资源id的查询条件
            queryWrapper.eq(TagsEntity.NAME, name);
        }
        //添加排序条件
        addSortQuery(queryWrapper);
        //是否包含删除的数据
        addIncludeDeleted(queryWrapper);
        return queryWrapper;
    }
}
