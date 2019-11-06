package com.uifuture.ssm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uifuture.ssm.entity.UsersEntity;
import com.uifuture.ssm.mapper.UsersMapper;
import com.uifuture.ssm.service.UsersService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author chenhx
 * @since 2019-09-12
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, UsersEntity> implements UsersService {

    /**
     * 查询用户名是否存在
     *
     * @param username 用户名
     * @return 0-表示不存在
     */
    @Override
    public Integer selectCountByUsername(String username) {
        return getCount(username, UsersEntity.USERNAME);
    }

    /**
     * 通过用户名查询用户数据
     * @param username 用户名
     * @return 用户数据，没有该用户返回NULL
     */
    @Override
    public UsersEntity selectByUsername(String username) {
        return getUsersEntity(username, UsersEntity.USERNAME);
    }

    /**
     * 查询邮箱是否存在
     * @param email 邮箱
     * @return 0-表示不存在
     */
    @Override
    public Integer selectCountByEmail(String email) {
        return getCount(email, UsersEntity.EMAIL);
    }

    /**
     * 通过邮箱查询用户数据
     * @param email 邮箱
     * @return 用户数据，查询不到用户返回NULL
     */
    @Override
    public UsersEntity selectByEmail(String email) {
        return getUsersEntity(email, UsersEntity.EMAIL);
    }

    /**
     * 通过唯一建获取数据是否存在
     *
     * @param value
     * @param column
     * @return
     */
    private Integer getCount(String value, String column) {
        QueryWrapper<UsersEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(column, value);
        return this.count(queryWrapper);
    }

    /**
     * 通过唯一建查询数据
     *
     * @param value
     * @param column
     * @return
     */
    private UsersEntity getUsersEntity(String value, String column) {
        QueryWrapper<UsersEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(column, value);
        return this.getOne(queryWrapper);
    }
}
