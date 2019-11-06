package com.uifuture.ssm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uifuture.ssm.base.BaseEntity;
import com.uifuture.ssm.entity.ResourceSubjectEntity;
import com.uifuture.ssm.mapper.ResourceSubjectMapper;
import com.uifuture.ssm.service.ResourceSubjectService;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * <p>
 * 资源专题表 服务实现类
 * </p>
 *
 * @author chenhx
 * @since 2019-09-18
 */
@Service
public class ResourceSubjectServiceImpl extends ServiceImpl<ResourceSubjectMapper, ResourceSubjectEntity> implements ResourceSubjectService {

    @Override
    public Collection<ResourceSubjectEntity> listNoDelete() {
        QueryWrapper<ResourceSubjectEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BaseEntity.DELETE_TIME, 0);
        return this.list(queryWrapper);
    }
}
