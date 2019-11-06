/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.convert;

import com.uifuture.ssm.dto.UsersCookieDTO;
import com.uifuture.ssm.dto.UsersFocusPageDTO;
import com.uifuture.ssm.entity.UsersEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

/**
 * users类拷贝
 *
 * @author chenhx
 * @version UsersConvert.java, v 0.1 2019-09-17 16:06 chenhx
 */
@Mapper
public interface UsersConvert {

    UsersConvert INSTANCE = Mappers.getMapper(UsersConvert.class);

    /**
     * Entity -> DTO
     *
     * @param entity
     * @return
     */
    UsersCookieDTO entityToDTO(UsersEntity entity);

    /**
     * DTO -> Entity
     *
     * @param entity
     * @return
     */
    UsersEntity dtoToEntity(UsersCookieDTO entity);

    /**
     * Entity集合 -> DTO集合
     *
     * @param entity
     * @return
     */
    List<UsersCookieDTO> entityToDTOList(Collection<UsersEntity> entity);

    /**
     * DTO集合 -> Entity集合
     *
     * @param entity
     * @return
     */
    List<UsersEntity> dtoToEntityList(Collection<UsersCookieDTO> entity);

    /**
     * Entity集合 -> DTO集合
     *
     * @param entity
     * @return
     */
    List<UsersFocusPageDTO> entityToUsersFocusPageDtoList(Collection<UsersEntity> entity);

    /**
     * DTO集合 -> Entity集合
     *
     * @param entity
     * @return
     */
    List<UsersEntity> usersFocusPageDtoToEntityList(Collection<UsersFocusPageDTO> entity);
}
