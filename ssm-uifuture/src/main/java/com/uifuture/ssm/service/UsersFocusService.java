package com.uifuture.ssm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.uifuture.ssm.bo.UsersFocusQueryBo;
import com.uifuture.ssm.entity.UsersFocusEntity;

/**
 * <p>
 * 用户关注表 服务类
 * </p>
 *
 * @author chenhx
 * @since 2019-09-19
 */
public interface UsersFocusService extends IService<UsersFocusEntity> {

    /**
     * 移除数据
     *
     * @param userId  关注者id
     * @param focusId 被关注者id
     * @return
     */
    boolean removeByUserIdAndFocusId(Integer userId, Integer focusId);

    /**
     * 移除数据
     *
     * @param userId  关注者id
     * @param focusId 被关注者id
     * @return
     */
    UsersFocusEntity getByUserIdAndFocusedId(Integer userId, Integer focusId);

    /**
     * 分页查询
     *
     * @param pageNo       当前页
     * @param pageSize     数据
     * @param fieldQueryBo 查询条件
     * @return
     */
    IPage<UsersFocusEntity> getPageByFocusId(int pageNo, int pageSize, UsersFocusQueryBo fieldQueryBo);

}
