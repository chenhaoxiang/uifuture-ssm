package com.uifuture.ssm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uifuture.ssm.base.BaseEntity;
import com.uifuture.ssm.entity.ResourceTypeEntity;
import com.uifuture.ssm.mapper.ResourceTypeMapper;
import com.uifuture.ssm.service.ResourceTypeService;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * <p>
 * 资源分类表。 服务实现类
 * </p>
 *
 * @author chenhx
 * @since 2019-09-18
 */
@Service
public class ResourceTypeServiceImpl extends ServiceImpl<ResourceTypeMapper, ResourceTypeEntity> implements ResourceTypeService {

    @Override
    public Collection<ResourceTypeEntity> listNoDelete() {
        QueryWrapper<ResourceTypeEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BaseEntity.DELETE_TIME, 0);
        return this.list(queryWrapper);
    }
}
