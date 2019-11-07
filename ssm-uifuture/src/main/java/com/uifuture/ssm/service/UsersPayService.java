package com.uifuture.ssm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.uifuture.ssm.bo.UsersPayQueryBo;
import com.uifuture.ssm.entity.UsersPayEntity;

import java.util.List;

/**
 * <p>
 * 用户支付信息详情表 服务类
 * </p>
 *
 * @author chenhx
 * @since 2019-11-06
 */
public interface UsersPayService extends IService<UsersPayEntity> {

    /**
     * 通过订单号获取订单
     *
     * @param orderNumber
     * @return
     */
    UsersPayEntity getByOrderNumber(String orderNumber);

    /**
     * 只查询订单状态
     *
     * @param orderNumber
     * @return
     */
    UsersPayEntity getStateByOrderNumber(String orderNumber);

    /**
     * 通过支付状态获取支付信息列表
     *
     * @param state
     * @return
     */
    List<UsersPayEntity> selectAllByState(Integer state);

    /**
     * 根据条件分页获取数据
     *
     * @param pageNum
     * @param pageSize
     * @param queryBo
     * @return
     */
    IPage<UsersPayEntity> getPage(Integer pageNum, Integer pageSize, UsersPayQueryBo queryBo);
}
