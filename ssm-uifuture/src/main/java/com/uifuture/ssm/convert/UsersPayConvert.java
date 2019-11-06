/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.convert;

import com.uifuture.ssm.entity.UsersPayEntity;
import com.uifuture.ssm.req.UsersPayReq;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author chenhx
 * @version UsersPayConvert.java, v 0.1 2019-11-06 20:54 chenhx
 */
@Mapper
public interface UsersPayConvert {

    UsersPayConvert INSTANCE = Mappers.getMapper(UsersPayConvert.class);

    UsersPayEntity usersPayReqToUsersPayEntity(UsersPayReq usersPayReq);
}
