package com.uifuture.ssm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uifuture.ssm.bo.RResourceSubjectQueryBo;
import com.uifuture.ssm.entity.RResourceSubjectEntity;
import com.uifuture.ssm.mapper.RResourceSubjectMapper;
import com.uifuture.ssm.service.RResourceSubjectService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 资源与专题关系表。 服务实现类
 * </p>
 *
 * @author chenhx
 * @since 2019-09-18
 */
@Service
public class RResourceSubjectServiceImpl extends ServiceImpl<RResourceSubjectMapper, RResourceSubjectEntity> implements RResourceSubjectService {

    @Override
    public IPage<RResourceSubjectEntity> getPage(Integer pageNum, Integer pageSize, RResourceSubjectQueryBo queryBo) {
        Page<RResourceSubjectEntity> page = new Page<>();
        page.setSize(pageSize);
        page.setCurrent(pageNum);
        QueryWrapper<RResourceSubjectEntity> queryWrapper = queryBo.buildQuery();
        return this.page(page, queryWrapper);
    }
}
