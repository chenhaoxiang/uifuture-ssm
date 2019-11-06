package com.uifuture.ssm.spring.ioc.demo14_7.config;

import com.uifuture.ssm.spring.ioc.demo14_7.dao.SpringIoCDao;
import com.uifuture.ssm.spring.ioc.demo14_7.dao.impl.SpringIoCDaoImpl;
import com.uifuture.ssm.spring.ioc.demo14_7.service.SpringIoCService;
import com.uifuture.ssm.spring.ioc.demo14_7.service.impl.SpringIoCServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 使用注解注入Bean
 */
@Configuration
public class BeanConfiguration {
    @Bean
    public SpringIoCDao springIoCDao() {
        return new SpringIoCDaoImpl();
    }

    @Bean
    public SpringIoCService springIoCService() {
        SpringIoCServiceImpl bean = new SpringIoCServiceImpl();
        //注入dao
        bean.setSpringIoCDao(springIoCDao());
        return bean;
    }
}
