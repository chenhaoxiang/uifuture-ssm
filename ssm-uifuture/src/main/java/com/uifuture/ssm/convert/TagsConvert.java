/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.convert;

import com.uifuture.ssm.dto.TagsDTO;
import com.uifuture.ssm.entity.TagsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

/**
 * @author chenhx
 * @version TagsConvert.java, v 0.1 2019-09-19 15:47 chenhx
 */
@Mapper
public interface TagsConvert {

    TagsConvert INSTANCE = Mappers.getMapper(TagsConvert.class);

    /**
     * Entity -> DTO
     *
     * @param entity
     * @return
     */
    TagsDTO entityToDTO(TagsEntity entity);

    /**
     * DTO -> Entity
     *
     * @param entity
     * @return
     */
    TagsEntity dtoToEntity(TagsDTO entity);

    /**
     * Entity集合 -> DTO集合
     *
     * @param entity
     * @return
     */
    List<TagsDTO> entityToDTOList(Collection<TagsEntity> entity);

    /**
     * DTO集合 -> Entity集合
     *
     * @param entity
     * @return
     */
    List<TagsEntity> dtoToEntityList(Collection<TagsDTO> entity);
}
