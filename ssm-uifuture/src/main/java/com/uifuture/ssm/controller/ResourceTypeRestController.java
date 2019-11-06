package com.uifuture.ssm.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.uifuture.ssm.base.BaseController;
import com.uifuture.ssm.base.page.Page;
import com.uifuture.ssm.bo.RResourceTypeQueryBo;
import com.uifuture.ssm.common.RedisConstants;
import com.uifuture.ssm.convert.ResourceConvert;
import com.uifuture.ssm.convert.ResourceTypeConvert;
import com.uifuture.ssm.dto.ResourcePageDTO;
import com.uifuture.ssm.dto.ResourceTypeDTO;
import com.uifuture.ssm.entity.RResourceTypeEntity;
import com.uifuture.ssm.entity.ResourceEntity;
import com.uifuture.ssm.entity.ResourceTypeEntity;
import com.uifuture.ssm.enums.DeleteEnum;
import com.uifuture.ssm.enums.ResultCodeEnum;
import com.uifuture.ssm.exception.CheckoutException;
import com.uifuture.ssm.redis.RedisClient;
import com.uifuture.ssm.result.ResultModel;
import com.uifuture.ssm.service.RResourceSubjectService;
import com.uifuture.ssm.service.RResourceTypeService;
import com.uifuture.ssm.service.ResourceService;
import com.uifuture.ssm.service.ResourceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资源分类表。 前端控制器
 * </p>
 *
 * @author chenhx
 * @since 2019-09-18
 */
@RestController
@RequestMapping("/resourceType")
public class ResourceTypeRestController extends BaseController {
    @Autowired
    private ResourceTypeService resourceTypeService;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private RResourceSubjectService rResourceSubjectService;

    @Autowired
    private RResourceTypeService rResourceTypeService;
    @Autowired
    private RedisClient redisClient;

    /**
     * 获取分类的资源 列表，分页
     *
     * @return
     */
    @RequestMapping(value = "/pageList", method = RequestMethod.POST)
    public ResultModel pageList(Integer typetId, Integer pageNum, Integer pageSize, HttpServletRequest
            request, HttpServletResponse response) {
        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 20;
        }
        if (typetId == null || typetId < 0) {
            throw new CheckoutException(ResultCodeEnum.PARAMETER_ERROR);
        }
        //获取分类
        ResourceTypeEntity resourceTypeEntity = resourceTypeService.getById(typetId);
        if (resourceTypeEntity == null) {
            throw new CheckoutException(ResultCodeEnum.PARAMETER_ERROR);
        }
        if (!DeleteEnum.NO_DELETE.getValue().equals(resourceTypeEntity.getDeleteTime())) {
            throw new CheckoutException(ResultCodeEnum.DATA_DOES_NOT_EXIST);
        }

        RResourceTypeQueryBo rResourceTypeQueryBo = new RResourceTypeQueryBo();
        rResourceTypeQueryBo.setResourceTypeId(typetId);
        rResourceTypeQueryBo.buildQuery();
        IPage<RResourceTypeEntity> entityIPage = rResourceTypeService.getPage(pageNum, pageSize, rResourceTypeQueryBo);

        List<RResourceTypeEntity> rResourceSubjectEntities = entityIPage.getRecords();
        List<Integer> resourceIds = new ArrayList<>();
        for (RResourceTypeEntity rResourceSubjectEntity : rResourceSubjectEntities) {
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
     * 获取分类的所有数据
     *
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.POST)
    public ResultModel all(HttpServletRequest request, HttpServletResponse response) {
        List<ResourceTypeDTO> resourceTypeDTOList = redisClient.get(RedisConstants.getAllResourceTypeKey()).getList(ResourceTypeDTO.class);
        if (!CollectionUtils.isEmpty(resourceTypeDTOList)) {
            return ResultModel.success(resourceTypeDTOList);
        }

        Collection<ResourceTypeEntity> resourceTypeEntities = resourceTypeService.listNoDelete();

        Map<Integer, List<ResourceTypeEntity>> resourceTypeListMap = new HashMap<>();
        for (ResourceTypeEntity resourceTypeEntity : resourceTypeEntities) {
            if (resourceTypeListMap.containsKey(resourceTypeEntity.getPid())) {
                List<ResourceTypeEntity> resourceTypeEntityList = resourceTypeListMap.get(resourceTypeEntity.getPid());
                //由于是引用，不用再重新put集合到Map中去了
                resourceTypeEntityList.add(resourceTypeEntity);
            } else {
                List<ResourceTypeEntity> resourceTypeEntityList = new ArrayList<>();
                resourceTypeEntityList.add(resourceTypeEntity);
                resourceTypeListMap.put(resourceTypeEntity.getPid(), resourceTypeEntityList);
            }
        }
        //按照结构分层,首先是获取最上层的数据
        Integer pid = 0;
        List<ResourceTypeEntity> resourceTypeEntityList = resourceTypeListMap.get(pid);
        if (!CollectionUtils.isEmpty(resourceTypeEntityList)) {
            for (ResourceTypeEntity resourceTypeEntity : resourceTypeEntityList) {
                ResourceTypeDTO resourceTypeDTO = ResourceTypeConvert.INSTANCE.entityTo(resourceTypeEntity);
                resourceTypeDTOList.add(resourceTypeDTO);
            }
        }

        //遍历第一层的节点
        for (ResourceTypeDTO resourceTypeDTO : resourceTypeDTOList) {
            List<ResourceTypeDTO> cResourceTypeDTOList = addChildNode(resourceTypeDTO, resourceTypeListMap);
            resourceTypeDTO.setResourceTypeDTOS(cResourceTypeDTOList);
        }
        //增加缓存
        redisClient.set(RedisConstants.getAllResourceTypeKey(), resourceTypeDTOList, RedisConstants.REG_MAX_TIME_1_DAY);
        return ResultModel.success(resourceTypeDTOList);
    }

    /**
     * 递归无限的分类层级
     *
     * @param resourceTypeDTO
     * @param resourceTypeListMap
     * @return
     */
    private List<ResourceTypeDTO> addChildNode(ResourceTypeDTO resourceTypeDTO, Map<Integer, List<ResourceTypeEntity>> resourceTypeListMap) {
        List<ResourceTypeDTO> cResourceTypeDTOList = new ArrayList<>();
        List<ResourceTypeEntity> resourceTypeEntityList = resourceTypeListMap.get(resourceTypeDTO.getId());
        if (!CollectionUtils.isEmpty(resourceTypeEntityList)) {
            for (ResourceTypeEntity resourceTypeEntity : resourceTypeEntityList) {
                ResourceTypeDTO resourceTypeDTO1 = ResourceTypeConvert.INSTANCE.entityTo(resourceTypeEntity);
                List<ResourceTypeDTO> resourceTypeDTOList = addChildNode(resourceTypeDTO1, resourceTypeListMap);
                resourceTypeDTO1.setResourceTypeDTOS(resourceTypeDTOList);
                cResourceTypeDTOList.add(resourceTypeDTO1);
            }
        }
        return cResourceTypeDTOList;
    }


}
