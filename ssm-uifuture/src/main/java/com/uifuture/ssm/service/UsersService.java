package com.uifuture.ssm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.uifuture.ssm.entity.UsersEntity;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author chenhx
 * @since 2019-09-12
 */
public interface UsersService extends IService<UsersEntity> {

    /**
     * 查询用户名是否存在
     *
     * @param username
     * @return
     */
    Integer selectCountByUsername(String username);


    /**
     * 通过用户名查询用户数据
     *
     * @param username
     * @return
     */
    UsersEntity selectByUsername(String username);


    /**
     * 查询邮箱是否存在
     *
     * @param email
     * @return
     */
    Integer selectCountByEmail(String email);


    /**
     * 通过邮箱查询用户数据
     *
     * @param email
     * @return
     */
    UsersEntity selectByEmail(String email);



}
