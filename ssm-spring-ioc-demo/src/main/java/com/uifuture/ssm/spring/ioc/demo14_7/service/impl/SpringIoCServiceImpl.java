package com.uifuture.ssm.spring.ioc.demo14_7.service.impl;

import com.uifuture.ssm.spring.ioc.demo14_7.dao.SpringIoCDao;
import com.uifuture.ssm.spring.ioc.demo14_7.service.SpringIoCService;

public class SpringIoCServiceImpl implements SpringIoCService {
    /**
     * 待注入对象
     */
    private SpringIoCDao springIoCDao;

    public void setSpringIoCDao(SpringIoCDao springIoCDao) {
        this.springIoCDao = springIoCDao;
    }

    @Override
    public void saySpringIoC() {
        System.out.println("SpringIoCServiceImpl->saySpringIoC()");
        springIoCDao.saySpringIoC();
    }
}
