package com.uifuture.ssm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.uifuture.ssm.bo.TagsQueryBo;
import com.uifuture.ssm.entity.TagsEntity;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 标签表。 服务类
 * </p>
 *
 * @author chenhx
 * @since 2019-09-18
 */
public interface TagsService extends IService<TagsEntity> {

    /**
     * 根据标签名称集合查询标签集合
     *
     * @param nameList
     * @return
     */
    List<TagsEntity> listByNameList(Collection<String> nameList);

    /**
     * 查询标签
     *
     * @param name
     * @return
     */
    TagsEntity getByName(String name);


    /**
     * 获取分页的标签数据
     *
     * @param pageNum
     * @param pageSize
     * @param tagsQueryBo
     * @return
     */
    IPage<TagsEntity> getPage(Integer pageNum, Integer pageSize, TagsQueryBo tagsQueryBo);
}
