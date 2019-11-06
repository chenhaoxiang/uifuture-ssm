package com.uifuture.ssm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uifuture.ssm.entity.RResourceSubjectEntity;
import com.uifuture.ssm.entity.RResourceTagsEntity;
import com.uifuture.ssm.entity.RResourceTypeEntity;
import com.uifuture.ssm.entity.ResourceContentEntity;
import com.uifuture.ssm.entity.ResourceEntity;
import com.uifuture.ssm.entity.TagsEntity;
import com.uifuture.ssm.entity.UsersEntity;
import com.uifuture.ssm.enums.ResourceStateEnum;
import com.uifuture.ssm.enums.ResultCodeEnum;
import com.uifuture.ssm.exception.ServiceException;
import com.uifuture.ssm.mapper.ResourceContentMapper;
import com.uifuture.ssm.mapper.ResourceMapper;
import com.uifuture.ssm.service.RResourceSubjectService;
import com.uifuture.ssm.service.RResourceTagsService;
import com.uifuture.ssm.service.RResourceTypeService;
import com.uifuture.ssm.service.ResourceService;
import com.uifuture.ssm.service.TagsService;
import com.uifuture.ssm.util.CollectionUtils;
import com.uifuture.ssm.util.PasswordUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 资源表。 服务实现类
 * </p>
 *
 * @author chenhx
 * @since 2019-09-18
 */
@Service
@Slf4j
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, ResourceEntity> implements ResourceService {

    /**
     * 事务管理器
     */
    @Autowired
    private DataSourceTransactionManager transactionManager;

    @Autowired
    private ResourceContentMapper resourceContentMapper;

    @Autowired
    private RResourceTypeService rResourceTypeService;

    @Autowired
    private RResourceSubjectService rResourceSubjectService;

    @Autowired
    private TagsService tagsService;
    @Autowired
    private RResourceTagsService rResourceTagsService;

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public Integer saveResource(ResourceEntity resourceEntity, ResourceContentEntity resourceContentEntity, UsersEntity usersEntity, List<Integer> typeIds, List<Integer> subjectIds, Set<String> tagsNames) {
        TransactionStatus transaction = null;
        try {
            // 开启事务
            transaction = transactionManager.getTransaction(new DefaultTransactionDefinition());

            //增加资源信息
            resourceEntity.setCreateId(usersEntity.getId());
            String token = PasswordUtils.getToken();
            resourceEntity.setToken(token);
            resourceEntity.setState(ResourceStateEnum.IN_THE_REVIEW.getValue());
            resourceEntity.setNewName(resourceEntity.getToken());
            this.save(resourceEntity);

            //增加资源详情
            resourceContentEntity.setResourceToken(token);
            resourceContentMapper.insert(resourceContentEntity);

            //增加类型关联
            List<RResourceTypeEntity> rResourceTypeEntities = new ArrayList<>();
            for (Integer typeId : typeIds) {
                RResourceTypeEntity rResourceTypeEntity = new RResourceTypeEntity();
                rResourceTypeEntity.setResourceId(resourceEntity.getId());
                rResourceTypeEntity.setResourceTypeId(typeId);
                rResourceTypeEntities.add(rResourceTypeEntity);
            }
            rResourceTypeService.saveBatch(rResourceTypeEntities);

            //增加专题关联
            List<RResourceSubjectEntity> rResourceSubjectEntities = new ArrayList<>();
            for (Integer subjectId : subjectIds) {
                RResourceSubjectEntity rResourceSubjectEntity = new RResourceSubjectEntity();
                rResourceSubjectEntity.setResourceId(resourceEntity.getId());
                rResourceSubjectEntity.setSubjectId(subjectId);
                rResourceSubjectEntities.add(rResourceSubjectEntity);
            }
            rResourceSubjectService.saveBatch(rResourceSubjectEntities);

            log.info("增加资源，resourceEntity={},", resourceEntity);
            //提交事务
            transactionManager.commit(transaction);

        } catch (Exception e) {
            if (transaction != null) {
                //回滚事务
                transactionManager.rollback(transaction);
            }
            log.error("发表资源失败", e);
            throw new ServiceException(ResultCodeEnum.BUSINESS_PROCESS_FAILED);
        }

        //增加标签,标签插入的成功对数据实际的影响并不大，放在事务之外进行
        List<TagsEntity> oldTags = tagsService.listByNameList(tagsNames);
        //将name抽取出来作为集合
        List<String> oldTagsNames = oldTags.stream().map(TagsEntity::getName).collect(Collectors.toList());
        Set<String> oldTagsNameSet = CollectionUtils.listToSet(oldTagsNames);

        List<RResourceTagsEntity> rResourcesTagsEntities = new ArrayList<>();
        for (String tagsName : tagsNames) {
            RResourceTagsEntity rResourcesTagsEntity = new RResourceTagsEntity();
            rResourcesTagsEntity.setResourceId(resourceEntity.getId());
            if (oldTagsNameSet.contains(tagsName)) {
                //原来存在的数据
                rResourcesTagsEntity.setTagsId(0);
            } else {
                //需要新增的标签
                TagsEntity tagsEntity = new TagsEntity();
                tagsEntity.setName(tagsName);
                //这里有可能出现并发的情况，由于数据库存在唯一建约束，可能会抛异常，如果出现异常，进行重新查询数据库看是否存在该名字的标签，进行处理
                try {
                    tagsService.save(tagsEntity);
                } catch (Exception e) {
                    TagsEntity tagsEntity1 = tagsService.getByName(tagsName);
                    if (tagsEntity1 != null) {
                        rResourcesTagsEntity.setTagsId(tagsEntity1.getId());
                    } else {
                        //否则抛弃该标签名，不进行插入数据库
                        continue;
                    }
                }
            }
            rResourcesTagsEntities.add(rResourcesTagsEntity);
        }
        rResourceTagsService.saveBatch(rResourcesTagsEntities);
        return 1;
    }

    @Override
    public ResourceEntity getByToken(String token) {
        QueryWrapper<ResourceEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ResourceEntity.TOKEN, token);
        return this.getOne(queryWrapper);
    }

    /**
     * 异步增加访问量
     *
     * @param token
     */
    @Async
    @Override
    public void addViewsOne(String token) {
        if (StringUtils.isEmpty(token)) {
            return;
        }
        resourceMapper.addParamByToken(token, ResourceEntity.VISIT_TIMES, 1);
    }
}
