package com.uifuture.ssm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uifuture.ssm.bo.RResourceTagsQueryBo;
import com.uifuture.ssm.entity.RResourceTagsEntity;
import com.uifuture.ssm.mapper.RResourceTagsMapper;
import com.uifuture.ssm.service.RResourceTagsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 资源标签关系表。 服务实现类
 * </p>
 *
 * @author chenhx
 * @since 2019-09-24
 */
@Service
public class RResourceTagsServiceImpl extends ServiceImpl<RResourceTagsMapper, RResourceTagsEntity> implements RResourceTagsService {
    @Override
    public List<RResourceTagsEntity> listByResourceId(Integer id) {
        QueryWrapper<RResourceTagsEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(RResourceTagsEntity.RESOURCE_ID, id);
        return this.list(queryWrapper);
    }

    @Override
    public IPage<RResourceTagsEntity> getPage(Integer pageNum, Integer pageSize, RResourceTagsQueryBo queryBo) {
        Page<RResourceTagsEntity> page = new Page<>();
        page.setSize(pageSize);
        page.setCurrent(pageNum);
        QueryWrapper<RResourceTagsEntity> queryWrapper = queryBo.buildQuery();
        return this.page(page, queryWrapper);
    }
}
