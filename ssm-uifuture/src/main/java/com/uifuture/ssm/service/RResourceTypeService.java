package com.uifuture.ssm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.uifuture.ssm.bo.RResourceTypeQueryBo;
import com.uifuture.ssm.entity.RResourceTypeEntity;

/**
 * <p>
 * 资源与资源分类关系表 服务类
 * </p>
 *
 * @author chenhx
 * @since 2019-09-18
 */
public interface RResourceTypeService extends IService<RResourceTypeEntity> {

    /**
     * 获取分页数据
     *
     * @param pageNum
     * @param pageSize
     * @param rResourceTypeQueryBo
     * @return
     */
    IPage<RResourceTypeEntity> getPage(Integer pageNum, Integer pageSize, RResourceTypeQueryBo rResourceTypeQueryBo);
}
