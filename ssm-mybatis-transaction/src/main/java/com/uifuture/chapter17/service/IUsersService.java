package com.uifuture.chapter17.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.uifuture.chapter17.domain.entity.UsersEntity;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author chenhx
 * @since 2019-08-23
 */
public interface IUsersService extends IService<UsersEntity> {
    /**
     * 通过用户名修改用户
     *
     * @param usersEntity
     * @return
     */
    Boolean updateByUsername(UsersEntity usersEntity);

    /**
     * 通过用户名修改金额
     *
     * @param money
     * @param username
     * @return
     */
    Boolean updateMoneyByUsername(Integer money, String username);

    /**
     * 转账接口
     *
     * @param fromName 转账方
     * @param toName   收账方
     * @param money    金额
     */
    void transfer(String fromName, String toName, Integer money);
}
