package com.uifuture.ssm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.uifuture.ssm.entity.ResourceContentEntity;
import com.uifuture.ssm.entity.ResourceEntity;
import com.uifuture.ssm.entity.UsersEntity;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 资源表。 服务类
 * </p>
 *
 * @author chenhx
 * @since 2019-09-18
 */
public interface ResourceService extends IService<ResourceEntity> {

    /**
     * 发布资源
     *
     * @param resourceEntity
     * @param resourceContentEntity
     * @param usersEntity
     * @param typeIds
     * @param subjectIds
     * @param tagsNames
     * @return
     */
    Integer saveResource(ResourceEntity resourceEntity, ResourceContentEntity resourceContentEntity, UsersEntity usersEntity, List<Integer> typeIds, List<Integer> subjectIds, Set<String> tagsNames);

    /**
     * 通过token获取资源信息
     *
     * @param token
     * @return
     */
    ResourceEntity getByToken(String token);

    /**
     * 增加访问量
     *
     * @param token
     */
    void addViewsOne(String token);

}
