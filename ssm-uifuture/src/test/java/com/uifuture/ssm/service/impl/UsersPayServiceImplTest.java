/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.uifuture.ssm.BaseTest;
import com.uifuture.ssm.bo.UsersPayQueryBo;
import com.uifuture.ssm.entity.UsersPayEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author chenhx
 * @version UsersPayServiceImplTest.java, v 0.1 2019-11-07 23:22 chenhx
 */
public class UsersPayServiceImplTest extends BaseTest {

    @Autowired
    private UsersPayServiceImpl usersPayService;


    @Test
    public void getPage() {

        //----------- Arrange -----------//
        UsersPayQueryBo usersPayQueryBo = new UsersPayQueryBo();
        IPage<UsersPayEntity> usersPayEntityIPage = usersPayService.getPage(1, 2, usersPayQueryBo);
        System.out.println(usersPayEntityIPage);
        //-----------   Act   -----------//


        //-----------  Assert -----------//
    }


}
