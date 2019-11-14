package com.uifuture.ssm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uifuture.ssm.entity.UsersEntity;
import com.uifuture.ssm.entity.UsersPayEntity;
import com.uifuture.ssm.entity.UsersRechargeUbEntity;
import com.uifuture.ssm.enums.ResultCodeEnum;
import com.uifuture.ssm.enums.UsersPayStateEnum;
import com.uifuture.ssm.exception.ParameterException;
import com.uifuture.ssm.mapper.UsersMapper;
import com.uifuture.ssm.service.UsersPayService;
import com.uifuture.ssm.service.UsersRechargeUbService;
import com.uifuture.ssm.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.math.BigDecimal;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author chenhx
 * @since 2019-09-12
 */
@Service
@Slf4j
public class UsersServiceImpl extends ServiceImpl<UsersMapper, UsersEntity> implements UsersService {

    @Autowired
    private UsersMapper usersMapper;
    /**
     * 事务
     */
    @Autowired
    private DataSourceTransactionManager transactionManager;

    @Autowired
    private UsersPayService usersPayService;

    @Autowired
    private UsersRechargeUbService usersRechargeUbService;

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
     *
     * @param username 用户名
     * @return 用户数据，没有该用户返回NULL
     */
    @Override
    public UsersEntity selectByUsername(String username) {
        return getUsersEntity(username, UsersEntity.USERNAME);
    }

    /**
     * 查询邮箱是否存在
     *
     * @param email 邮箱
     * @return 0-表示不存在
     */
    @Override
    public Integer selectCountByEmail(String email) {
        return getCount(email, UsersEntity.EMAIL);
    }

    /**
     * 通过邮箱查询用户数据
     *
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

    @Override
    public void addUB(UsersPayEntity usersPayEntity) {
        if (usersPayEntity == null) {
            throw new ParameterException(ResultCodeEnum.PARAMETER_ERROR);
        }
        //修改支付信息的状态
        UsersPayEntity updateUsersPay = new UsersPayEntity();
        updateUsersPay.setId(usersPayEntity.getId());
        updateUsersPay.setState(UsersPayStateEnum.ADOPT.getValue());
        //记录用户的增加UB信息
        UsersRechargeUbEntity usersRechargeUbEntity = new UsersRechargeUbEntity();
        usersRechargeUbEntity.setUserId(usersPayEntity.getUsersId());
        usersRechargeUbEntity.setMoney(usersPayEntity.getMoney());
        Integer ub = usersPayEntity.getMoney().multiply(new BigDecimal("100")).intValue();
        usersRechargeUbEntity.setUbNumber(ub);
        usersRechargeUbEntity.setOrderNumber(usersPayEntity.getOrderNumber());
        usersRechargeUbEntity.setUsersPayId(usersPayEntity.getId());
        usersRechargeUbEntity.setPayTypeEnName(usersPayEntity.getPayTypeEnName());

        //用户增加UB
        UsersEntity usersEntity = this.getById(usersPayEntity.getUsersId());
        if (usersEntity == null) {
            throw new ParameterException(ResultCodeEnum.USERS_DOES_NOT_EXIST);
        }

        //开启事务
        TransactionStatus transaction = null;
        try {
            transaction = transactionManager.getTransaction(new DefaultTransactionDefinition());

            usersPayService.updateById(updateUsersPay);

            usersRechargeUbService.save(usersRechargeUbEntity);

            usersMapper.operateUB(usersPayEntity.getUsersId(), ub);

            //事务提交
            transactionManager.commit(transaction);
        } catch (Exception e) {
            log.error("[UsersServiceImpl->addUB]，事务回滚", e);
            if (transaction != null) {
                //回滚
                transactionManager.rollback(transaction);
            }
            //TODO 进行发送MQ消息通知

        }

    }


}
