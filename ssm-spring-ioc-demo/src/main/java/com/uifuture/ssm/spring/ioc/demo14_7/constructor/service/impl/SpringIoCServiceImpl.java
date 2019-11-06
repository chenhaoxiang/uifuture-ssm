package com.uifuture.ssm.spring.ioc.demo14_7.constructor.service.impl;


import com.uifuture.ssm.spring.ioc.demo14_7.dao.SpringIoCDao;
import com.uifuture.ssm.spring.ioc.demo14_7.service.SpringIoCService;

public class SpringIoCServiceImpl implements SpringIoCService {
    /**
     * 待注入对象
     */
    private SpringIoCDao springIoCDao;

    /**
     * 构造函数注入对象
     *
     * @param springIoCDao
     */
    public SpringIoCServiceImpl(SpringIoCDao springIoCDao) {
        this.springIoCDao = springIoCDao;
    }

    @Override
    public void saySpringIoC() {
        System.out.println("constructor->SpringIoCServiceImpl->saySpringIoC()");
        springIoCDao.saySpringIoC();
    }
}
