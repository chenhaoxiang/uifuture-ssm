/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.convert;

import com.uifuture.ssm.dto.ResourceTypeDTO;
import com.uifuture.ssm.entity.ResourceTypeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author chenhx
 * @version ResourceTypeConvert.java, v 0.1 2019-09-25 00:49 chenhx
 */
@Mapper
public interface ResourceTypeConvert {

    ResourceTypeConvert INSTANCE = Mappers.getMapper(ResourceTypeConvert.class);

    /**
     * Entity -> DTO
     *
     * @param entity
     * @return
     */
    ResourceTypeDTO entityTo(ResourceTypeEntity entity);

    /**
     * DTO -> Entity
     *
     * @param entity
     * @return
     */
    ResourceTypeEntity toEntity(ResourceTypeDTO entity);

}
