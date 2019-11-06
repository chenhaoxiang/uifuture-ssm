package com.uifuture.ssm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.uifuture.ssm.entity.ResourceTypeEntity;

import java.util.Collection;

/**
 * <p>
 * 资源分类表。 服务类
 * </p>
 *
 * @author chenhx
 * @since 2019-09-18
 */
public interface ResourceTypeService extends IService<ResourceTypeEntity> {

    /**
     * 获取未删除的所有数据
     *
     * @return
     */
    Collection<ResourceTypeEntity> listNoDelete();
}
