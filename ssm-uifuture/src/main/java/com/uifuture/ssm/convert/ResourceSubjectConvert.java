/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.convert;

import com.uifuture.ssm.dto.ResourceSubjectDTO;
import com.uifuture.ssm.entity.ResourceSubjectEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

/**
 * @author chenhx
 * @version ResourceTypeConvert.java, v 0.1 2019-09-25 00:49 chenhx
 */
@Mapper
public interface ResourceSubjectConvert {

    ResourceSubjectConvert INSTANCE = Mappers.getMapper(ResourceSubjectConvert.class);

    /**
     * Entity -> DTO
     *
     * @param entity
     * @return
     */
    ResourceSubjectDTO entityTo(ResourceSubjectEntity entity);

    /**
     * DTO -> Entity
     *
     * @param entity
     * @return
     */
    ResourceSubjectEntity toEntity(ResourceSubjectDTO entity);

    /**
     * Entity -> DTO
     *
     * @param entity
     * @return
     */
    List<ResourceSubjectDTO> entityTo(Collection<ResourceSubjectEntity> entity);

    /**
     * DTO -> Entity
     *
     * @param entity
     * @return
     */
    List<ResourceSubjectEntity> toEntity(Collection<ResourceSubjectDTO> entity);

}
