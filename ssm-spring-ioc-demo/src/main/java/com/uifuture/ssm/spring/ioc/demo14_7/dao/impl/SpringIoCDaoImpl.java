package com.uifuture.ssm.spring.ioc.demo14_7.dao.impl;

import com.uifuture.ssm.spring.ioc.demo14_7.dao.SpringIoCDao;

/**
 * 实现类
 */
public class SpringIoCDaoImpl implements SpringIoCDao {
    @Override
    public void saySpringIoC() {
        System.out.println("SpringIoCDaoImpl->saySpringIoC()");
    }
}
