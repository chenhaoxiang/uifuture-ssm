package com.uifuture.ssm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.uifuture.ssm.bo.RResourceSubjectQueryBo;
import com.uifuture.ssm.entity.RResourceSubjectEntity;

/**
 * <p>
 * 资源与专题关系表。 服务类
 * </p>
 *
 * @author chenhx
 * @since 2019-09-18
 */
public interface RResourceSubjectService extends IService<RResourceSubjectEntity> {

    /**
     * 分页查询
     *
     * @param pageNum
     * @param pageSize
     * @param rResourceSubjectQueryBo
     * @return
     */
    IPage<RResourceSubjectEntity> getPage(Integer pageNum, Integer pageSize, RResourceSubjectQueryBo rResourceSubjectQueryBo);
}
