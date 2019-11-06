package com.uifuture.ssm.bo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uifuture.ssm.base.BaseQueryBo;
import com.uifuture.ssm.entity.RUsersCollectionsEntity;
import lombok.Data;

/**
 * <p>
 * 用户收藏资源相关表。
 * </p>
 *
 * @author chenhx
 * @since 2019-09-21
 */
@Data
public class RUsersCollectionsQueryBo extends BaseQueryBo<RUsersCollectionsEntity> {
    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 资源id
     */
    private Integer resourceId;

    @Override
    public QueryWrapper<RUsersCollectionsEntity> buildQuery() {
        QueryWrapper<RUsersCollectionsEntity> queryWrapper = new QueryWrapper<>();
        addId(queryWrapper);
        if (userId != null) {
            queryWrapper.eq(RUsersCollectionsEntity.USER_ID, userId);

        }
        if (resourceId != null) {
            queryWrapper.eq(RUsersCollectionsEntity.RESOURCE_ID, resourceId);

        }
        addSortQuery(queryWrapper);
        addIncludeDeleted(queryWrapper);
        return queryWrapper;
    }
}
