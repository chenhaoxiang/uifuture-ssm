package com.uifuture.ssm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.uifuture.ssm.bo.RUsersCollectionsQueryBo;
import com.uifuture.ssm.entity.RUsersCollectionsEntity;

/**
 * <p>
 * 用户收藏资源相关表。 服务类
 * </p>
 *
 * @author chenhx
 * @since 2019-09-21
 */
public interface RUsersCollectionsService extends IService<RUsersCollectionsEntity> {

    /**
     * 移除资源收藏
     *
     * @param userId     用户id
     * @param resourceId 资源id
     */
    void removeByUserIdAndResourceId(Integer userId, Integer resourceId);

    /**
     * 根据条件进行分页查询
     *
     * @param pageNum
     * @param pageSize
     * @param rUsersCollectionsQueryBo
     * @return
     */
    IPage<RUsersCollectionsEntity> getPage(Integer pageNum, Integer pageSize, RUsersCollectionsQueryBo rUsersCollectionsQueryBo);
}
