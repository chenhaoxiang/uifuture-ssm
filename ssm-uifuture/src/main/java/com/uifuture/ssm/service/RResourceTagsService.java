package com.uifuture.ssm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.uifuture.ssm.bo.RResourceTagsQueryBo;
import com.uifuture.ssm.entity.RResourceTagsEntity;

import java.util.List;

/**
 * <p>
 * 资源标签关系表。 服务类
 * </p>
 *
 * @author chenhx
 * @since 2019-09-24
 */
public interface RResourceTagsService extends IService<RResourceTagsEntity> {

    /**
     * 通过资源id查询标签 数据
     *
     * @param id
     * @return
     */
    List<RResourceTagsEntity> listByResourceId(Integer id);

    /**
     * 分页查询
     *
     * @param pageNum
     * @param pageSize
     * @param rResourceTagsQueryBo
     * @return
     */
    IPage<RResourceTagsEntity> getPage(Integer pageNum, Integer pageSize, RResourceTagsQueryBo rResourceTagsQueryBo);
}
