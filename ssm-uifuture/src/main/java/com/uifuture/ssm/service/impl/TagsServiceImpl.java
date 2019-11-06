package com.uifuture.ssm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uifuture.ssm.bo.TagsQueryBo;
import com.uifuture.ssm.entity.TagsEntity;
import com.uifuture.ssm.mapper.TagsMapper;
import com.uifuture.ssm.service.TagsService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 标签表。 服务实现类
 * </p>
 *
 * @author chenhx
 * @since 2019-09-18
 */
@Service
public class TagsServiceImpl extends ServiceImpl<TagsMapper, TagsEntity> implements TagsService {

    @Override
    public List<TagsEntity> listByNameList(Collection<String> nameList) {
        QueryWrapper<TagsEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(TagsEntity.NAME, nameList);
        return this.list(queryWrapper);
    }

    @Override
    public TagsEntity getByName(String name) {
        QueryWrapper<TagsEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(TagsEntity.NAME, name);
        return this.getOne(queryWrapper);
    }


    @Override
    public IPage<TagsEntity> getPage(Integer pageNum, Integer pageSize, TagsQueryBo tagsQueryBo) {
        Page<TagsEntity> page = new Page<>();
        page.setSize(pageSize);
        page.setCurrent(pageNum);
        QueryWrapper<TagsEntity> queryWrapper = tagsQueryBo.buildQuery();
        return this.page(page, queryWrapper);
    }
}
