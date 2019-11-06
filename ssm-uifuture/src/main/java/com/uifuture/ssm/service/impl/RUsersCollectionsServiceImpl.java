package com.uifuture.ssm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uifuture.ssm.bo.RUsersCollectionsQueryBo;
import com.uifuture.ssm.entity.RUsersCollectionsEntity;
import com.uifuture.ssm.mapper.RUsersCollectionsMapper;
import com.uifuture.ssm.service.RUsersCollectionsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户收藏资源相关表。 服务实现类
 * </p>
 *
 * @author chenhx
 * @since 2019-09-21
 */
@Service
public class RUsersCollectionsServiceImpl extends ServiceImpl<RUsersCollectionsMapper, RUsersCollectionsEntity> implements RUsersCollectionsService {

    @Override
    public void removeByUserIdAndResourceId(Integer userId, Integer resourceId) {
        QueryWrapper<RUsersCollectionsEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(RUsersCollectionsEntity.USER_ID, userId);
        queryWrapper.eq(RUsersCollectionsEntity.RESOURCE_ID, resourceId);
        this.remove(queryWrapper);
    }

    @Override
    public IPage<RUsersCollectionsEntity> getPage(Integer pageNum, Integer pageSize, RUsersCollectionsQueryBo rUsersCollectionsQueryBo) {
        Page<RUsersCollectionsEntity> page = new Page<>();
        page.setSize(pageSize);
        page.setCurrent(pageNum);
        QueryWrapper<RUsersCollectionsEntity> queryWrapper = rUsersCollectionsQueryBo.buildQuery();
        return this.page(page, queryWrapper);
    }
}
