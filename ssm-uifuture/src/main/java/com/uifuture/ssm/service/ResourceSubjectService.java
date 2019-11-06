package com.uifuture.ssm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.uifuture.ssm.entity.ResourceSubjectEntity;

import java.util.Collection;

/**
 * <p>
 * 资源专题表 服务类
 * </p>
 *
 * @author chenhx
 * @since 2019-09-18
 */
public interface ResourceSubjectService extends IService<ResourceSubjectEntity> {
    /**
     * 获取未删除的全部数据
     *
     * @return
     */
    Collection<ResourceSubjectEntity> listNoDelete();
}
