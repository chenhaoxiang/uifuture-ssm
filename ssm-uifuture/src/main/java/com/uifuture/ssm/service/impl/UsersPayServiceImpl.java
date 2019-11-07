package com.uifuture.ssm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uifuture.ssm.bo.UsersPayQueryBo;
import com.uifuture.ssm.entity.UsersPayEntity;
import com.uifuture.ssm.mapper.UsersPayMapper;
import com.uifuture.ssm.service.UsersPayService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户支付信息详情表 服务实现类
 * </p>
 *
 * @author chenhx
 * @since 2019-11-06
 */
@Service
public class UsersPayServiceImpl extends ServiceImpl<UsersPayMapper, UsersPayEntity> implements UsersPayService {

    @Override
    public UsersPayEntity getByOrderNumber(String orderNumber) {
        QueryWrapper<UsersPayEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(UsersPayEntity.ORDER_NUMBER, orderNumber);
        return this.getOne(queryWrapper);
    }

    @Override
    public UsersPayEntity getStateByOrderNumber(String orderNumber) {
        QueryWrapper<UsersPayEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(UsersPayEntity.STATE);
        queryWrapper.eq(UsersPayEntity.ORDER_NUMBER, orderNumber);
        return this.getOne(queryWrapper);
    }

    @Override
    public List<UsersPayEntity> selectAllByState(Integer state) {
        QueryWrapper<UsersPayEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(UsersPayEntity.STATE, state);
        return this.list(queryWrapper);
    }


    @Override
    public IPage<UsersPayEntity> getPage(Integer pageNum, Integer pageSize, UsersPayQueryBo queryBo) {
        Page<UsersPayEntity> page = new Page<>();
        page.setSize(pageSize);
        page.setCurrent(pageNum);
        QueryWrapper<UsersPayEntity> queryWrapper = queryBo.buildQuery();
        return this.page(page, queryWrapper);
    }
}
