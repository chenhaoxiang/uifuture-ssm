package com.uifuture.ssm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uifuture.ssm.bo.RResourceTypeQueryBo;
import com.uifuture.ssm.entity.RResourceTypeEntity;
import com.uifuture.ssm.mapper.RResourceTypeMapper;
import com.uifuture.ssm.service.RResourceTypeService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 资源与资源分类关系表 服务实现类
 * </p>
 *
 * @author chenhx
 * @since 2019-09-18
 */
@Service
public class RResourceTypeServiceImpl extends ServiceImpl<RResourceTypeMapper, RResourceTypeEntity> implements RResourceTypeService {

    @Override
    public IPage<RResourceTypeEntity> getPage(Integer pageNum, Integer pageSize, RResourceTypeQueryBo queryBo) {
        Page<RResourceTypeEntity> page = new Page<>();
        page.setSize(pageSize);
        page.setCurrent(pageNum);
        QueryWrapper<RResourceTypeEntity> queryWrapper = queryBo.buildQuery();
        return this.page(page, queryWrapper);
    }
}
