package com.uifuture.ssm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uifuture.ssm.bo.UsersFocusQueryBo;
import com.uifuture.ssm.entity.UsersFocusEntity;
import com.uifuture.ssm.mapper.UsersFocusMapper;
import com.uifuture.ssm.service.UsersFocusService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户关注表 服务实现类
 * </p>
 *
 * @author chenhx
 * @since 2019-09-19
 */
@Service
public class UsersFocusServiceImpl extends ServiceImpl<UsersFocusMapper, UsersFocusEntity> implements UsersFocusService {

    @Override
    public boolean removeByUserIdAndFocusId(Integer userId, Integer focusId) {
        QueryWrapper<UsersFocusEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(UsersFocusEntity.USER_ID, userId);
        queryWrapper.eq(UsersFocusEntity.FOCUSED_USER_ID, focusId);
        return this.remove(queryWrapper);
    }

    @Override
    public UsersFocusEntity getByUserIdAndFocusedId(Integer userId, Integer focusId) {
        QueryWrapper<UsersFocusEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(UsersFocusEntity.USER_ID, userId);
        queryWrapper.eq(UsersFocusEntity.FOCUSED_USER_ID, focusId);
        return this.getOne(queryWrapper);
    }

    @Override
    public IPage<UsersFocusEntity> getPageByFocusId(int pageNo, int pageSize, UsersFocusQueryBo fieldQueryBo) {
        Page<UsersFocusEntity> page = new Page<>();
        page.setSize(pageSize);
        page.setCurrent(pageNo);
        QueryWrapper<UsersFocusEntity> queryWrapper = fieldQueryBo.buildQuery();
        return this.page(page, queryWrapper);
    }
}
