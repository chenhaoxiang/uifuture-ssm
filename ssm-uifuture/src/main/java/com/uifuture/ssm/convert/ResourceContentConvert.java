/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.convert;

import com.uifuture.ssm.dto.ResourceContentDTO;
import com.uifuture.ssm.entity.ResourceContentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author chenhx
 * @version ResourceContentConvert.java, v 0.1 2019-09-18 18:07 chenhx
 */
@Mapper
public interface ResourceContentConvert {


    ResourceContentConvert INSTANCE = Mappers.getMapper(ResourceContentConvert.class);

    /**
     * Entity -> DTO
     *
     * @param entity
     * @return
     */
    ResourceContentDTO entityToDto(ResourceContentEntity entity);

    /**
     * DTO -> Entity
     *
     * @param entity
     * @return
     */
    ResourceContentEntity dtoToEntity(ResourceContentDTO entity);


}
