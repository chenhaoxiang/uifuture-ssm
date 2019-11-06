/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.service.impl;

import com.uifuture.ssm.BaseTest;
import com.uifuture.ssm.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author chenhx
 * @version UsersServiceImplTest.java, v 0.1 2019-09-13 09:26 chenhx
 */
@Slf4j
public class UsersServiceImplTest extends BaseTest {
    @Autowired
    private UsersService usersService;

    @Test
    public void list() {
        log.info("获取的数据：" + usersService.list());
    }


}
