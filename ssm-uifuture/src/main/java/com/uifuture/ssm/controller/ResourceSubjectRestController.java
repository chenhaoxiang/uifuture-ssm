package com.uifuture.ssm.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.uifuture.ssm.base.BaseController;
import com.uifuture.ssm.base.page.Page;
import com.uifuture.ssm.bo.RResourceSubjectQueryBo;
import com.uifuture.ssm.convert.ResourceConvert;
import com.uifuture.ssm.convert.ResourceSubjectConvert;
import com.uifuture.ssm.dto.ResourcePageDTO;
import com.uifuture.ssm.dto.ResourceSubjectDTO;
import com.uifuture.ssm.entity.RResourceSubjectEntity;
import com.uifuture.ssm.entity.ResourceEntity;
import com.uifuture.ssm.entity.ResourceSubjectEntity;
import com.uifuture.ssm.enums.DeleteEnum;
import com.uifuture.ssm.enums.ResultCodeEnum;
import com.uifuture.ssm.exception.CheckoutException;
import com.uifuture.ssm.result.ResultModel;
import com.uifuture.ssm.service.RResourceSubjectService;
import com.uifuture.ssm.service.ResourceService;
import com.uifuture.ssm.service.ResourceSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 资源专题表 前端控制器
 * </p>
 *
 * @author chenhx
 * @since 2019-09-18
 */
@RestController
@RequestMapping("/resourceSubject")
public class ResourceSubjectRestController extends BaseController {
    @Autowired
    private ResourceSubjectService resourceSubjectService;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private RResourceSubjectService rResourceSubjectService;

    /**
     * 获取专题的资源 列表，分页
     *
     * @return
     */
    @RequestMapping(value = "/pageList", method = RequestMethod.POST)
    public ResultModel pageList(Integer subjectId, Integer pageNum, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 20;
        }
        if (subjectId == null || subjectId < 0) {
            throw new CheckoutException(ResultCodeEnum.PARAMETER_ERROR);
        }
        //获取专题
        ResourceSubjectEntity resourceSubjectEntity = resourceSubjectService.getById(subjectId);
        if (resourceSubjectEntity == null) {
            throw new CheckoutException(ResultCodeEnum.PARAMETER_ERROR);
        }
        if (!DeleteEnum.NO_DELETE.getValue().equals(resourceSubjectEntity.getDeleteTime())) {
            throw new CheckoutException(ResultCodeEnum.DATA_DOES_NOT_EXIST);
        }

        RResourceSubjectQueryBo rResourceSubjectQueryBo = new RResourceSubjectQueryBo();
        rResourceSubjectQueryBo.setSubjectId(subjectId);
        rResourceSubjectQueryBo.buildQuery();
        IPage<RResourceSubjectEntity> entityIPage = rResourceSubjectService.getPage(pageNum, pageSize, rResourceSubjectQueryBo);

        List<RResourceSubjectEntity> rResourceSubjectEntities = entityIPage.getRecords();
        List<Integer> resourceIds = new ArrayList<>();
        for (RResourceSubjectEntity rResourceSubjectEntity : rResourceSubjectEntities) {
            //资源id
            resourceIds.add(rResourceSubjectEntity.getResourceId());
        }
        Collection<ResourceEntity> resourceEntities = new ArrayList<>();
        if (!CollectionUtils.isEmpty(resourceIds)) {
            resourceEntities = resourceService.listByIds(resourceIds);
        }

        Page<ResourcePageDTO> resourcePageDTOPage = new Page<>();
        resourcePageDTOPage.setPageSize((int) entityIPage.getSize());
        resourcePageDTOPage.setCurrentIndex((int) entityIPage.getCurrent());
        resourcePageDTOPage.setTotalNumber((int) entityIPage.getTotal());

        List<ResourcePageDTO> resourcePageDTOS = ResourceConvert.INSTANCE.entityToPageList(resourceEntities);
        resourcePageDTOPage.setItems(resourcePageDTOS);
        return ResultModel.success(resourcePageDTOPage);
    }

    /**
     * 获取专题
     *
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.POST)
    public ResultModel all() {
        Collection<ResourceSubjectEntity> resourceSubjectEntities = resourceSubjectService.listNoDelete();
        List<ResourceSubjectDTO> resourceSubjectDTOS = ResourceSubjectConvert.INSTANCE.entityTo(resourceSubjectEntities);
        return ResultModel.success(resourceSubjectDTOS);
    }


}
