package com.uifuture.ssm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.uifuture.ssm.entity.ResourceContentEntity;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author chenhx
 * @since 2019-09-18
 */
public interface ResourceContentService extends IService<ResourceContentEntity> {

    /**
     * 通过资源token获取资源
     *
     * @param token
     * @return
     */
    ResourceContentEntity getByToken(String token);
}
