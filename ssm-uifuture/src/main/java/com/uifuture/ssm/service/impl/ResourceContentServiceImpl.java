package com.uifuture.ssm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uifuture.ssm.entity.ResourceContentEntity;
import com.uifuture.ssm.mapper.ResourceContentMapper;
import com.uifuture.ssm.service.ResourceContentService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author chenhx
 * @since 2019-09-18
 */
@Service
public class ResourceContentServiceImpl extends ServiceImpl<ResourceContentMapper, ResourceContentEntity> implements ResourceContentService {

    @Override
    public ResourceContentEntity getByToken(String token) {
        QueryWrapper<ResourceContentEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ResourceContentEntity.RESOURCE_TOKEN, token);
        return this.getOne(queryWrapper);
    }
}
