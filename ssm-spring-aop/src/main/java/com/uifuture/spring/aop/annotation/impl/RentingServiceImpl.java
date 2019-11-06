/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.spring.aop.annotation.impl;

import com.uifuture.spring.aop.annotation.RentingService;
import org.springframework.stereotype.Service;

/**
 * @author chenhx
 * @version RentingServiceImpl.java, v 0.1 2019-07-24 20:45 chenhx
 */
@Service("rentingServiceImpl")
public class RentingServiceImpl implements RentingService {

    /**
     * 房东的核心业务功能
     */
    @Override
    public void service() {
        //输出仅仅代表业务处理
        System.out.println("签合同...");
        System.out.println("收房租...");
    }

}
