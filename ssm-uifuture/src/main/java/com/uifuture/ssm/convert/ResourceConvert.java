/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.convert;

import com.uifuture.ssm.dto.RUsersCollectionsPageDTO;
import com.uifuture.ssm.dto.ResourceDTO;
import com.uifuture.ssm.dto.ResourcePageDTO;
import com.uifuture.ssm.entity.ResourceEntity;
import com.uifuture.ssm.req.ResourceReq;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

/**
 * @author chenhx
 * @version ResourceConvert.java, v 0.1 2019-09-18 11:47 chenhx
 */
@Mapper
public interface ResourceConvert {

    ResourceConvert INSTANCE = Mappers.getMapper(ResourceConvert.class);

    /**
     * Entity -> DTO
     *
     * @param entity
     * @return
     */
    ResourceReq entityTo(ResourceEntity entity);

    /**
     * DTO -> Entity
     *
     * @param entity
     * @return
     */
    ResourceEntity toEntity(ResourceReq entity);

    /**
     * Entity -> DTO
     *
     * @param entity
     * @return
     */
    ResourceDTO entityToDto(ResourceEntity entity);

    /**
     * DTO -> Entity
     *
     * @param entity
     * @return
     */
    ResourceEntity dtoToEntity(ResourceDTO entity);

    /**
     * @param resourceEntities
     * @return
     */
    List<RUsersCollectionsPageDTO> entityToRUsersCollectionsPageDto(Collection<ResourceEntity> resourceEntities);

    RUsersCollectionsPageDTO entityToRUsersCollectionsPageDto(ResourceEntity resourceEntities);


    ResourcePageDTO entityToPageDto(ResourceEntity entity);

    List<ResourcePageDTO> entityToPageList(Collection<ResourceEntity> resourceEntities);
}
