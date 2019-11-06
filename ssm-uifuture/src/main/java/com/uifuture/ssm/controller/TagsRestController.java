package com.uifuture.ssm.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.uifuture.ssm.base.BaseController;
import com.uifuture.ssm.base.page.Page;
import com.uifuture.ssm.bo.RResourceTagsQueryBo;
import com.uifuture.ssm.bo.TagsQueryBo;
import com.uifuture.ssm.convert.ResourceConvert;
import com.uifuture.ssm.convert.TagsConvert;
import com.uifuture.ssm.dto.ResourcePageDTO;
import com.uifuture.ssm.dto.TagsDTO;
import com.uifuture.ssm.entity.RResourceTagsEntity;
import com.uifuture.ssm.entity.ResourceEntity;
import com.uifuture.ssm.entity.TagsEntity;
import com.uifuture.ssm.enums.DeleteEnum;
import com.uifuture.ssm.enums.ResultCodeEnum;
import com.uifuture.ssm.exception.CheckoutException;
import com.uifuture.ssm.result.ResultModel;
import com.uifuture.ssm.service.RResourceTagsService;
import com.uifuture.ssm.service.ResourceService;
import com.uifuture.ssm.service.TagsService;
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
 * 标签表。 前端控制器
 * </p>
 *
 * @author chenhx
 * @since 2019-09-18
 */
@RestController
@RequestMapping("/tags")
public class TagsRestController extends BaseController {


    @Autowired
    private TagsService tagsService;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private RResourceTagsService rResourceTagsService;

    /**
     * 获取标签的资源 列表，分页
     *
     * @return
     */
    @RequestMapping(value = "/pageList", method = RequestMethod.POST)
    public ResultModel pageList(Integer tagsId, Integer pageNum, Integer pageSize, HttpServletRequest
            request, HttpServletResponse response) {
        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 20;
        }
        if (tagsId == null || tagsId < 0) {
            throw new CheckoutException(ResultCodeEnum.PARAMETER_ERROR);
        }
        //获取分类
        TagsEntity tagsEntity = tagsService.getById(tagsId);
        if (tagsEntity == null) {
            throw new CheckoutException(ResultCodeEnum.PARAMETER_ERROR);
        }
        if (!DeleteEnum.NO_DELETE.getValue().equals(tagsEntity.getDeleteTime())) {
            throw new CheckoutException(ResultCodeEnum.DATA_DOES_NOT_EXIST);
        }

        RResourceTagsQueryBo rResourceTagsQueryBo = new RResourceTagsQueryBo();
        rResourceTagsQueryBo.setTagsId(tagsId);
        rResourceTagsQueryBo.buildQuery();
        IPage<RResourceTagsEntity> entityIPage = rResourceTagsService.getPage(pageNum, pageSize, rResourceTagsQueryBo);

        List<RResourceTagsEntity> rResourceSubjectEntities = entityIPage.getRecords();
        List<Integer> resourceIds = new ArrayList<>();
        for (RResourceTagsEntity rResourceSubjectEntity : rResourceSubjectEntities) {
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
     * 获取标签的分页数据
     *
     * @return
     */
    @RequestMapping(value = "/pageTagsList", method = RequestMethod.POST)
    public ResultModel pageTagsList(Integer pageNum, Integer pageSize, HttpServletRequest
            request, HttpServletResponse response) {
        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 20;
        }
        TagsQueryBo tagsQueryBo = new TagsQueryBo();
        //查询分页数据
        IPage<TagsEntity> tagsEntityIPage = tagsService.getPage(pageNum, pageSize, tagsQueryBo);
        //设置分页数据
        Page<TagsDTO> tagsDTOPage = new Page<>();
        tagsDTOPage.setPageSize((int) tagsEntityIPage.getSize());
        tagsDTOPage.setCurrentIndex((int) tagsEntityIPage.getCurrent());
        tagsDTOPage.setTotalNumber((int) tagsEntityIPage.getTotal());
        //拷贝集合数据
        List<TagsDTO> resourcePageDTOS = TagsConvert.INSTANCE.entityToDTOList(tagsEntityIPage.getRecords());
        tagsDTOPage.setItems(resourcePageDTOS);
        return ResultModel.success(tagsDTOPage);
    }


}
