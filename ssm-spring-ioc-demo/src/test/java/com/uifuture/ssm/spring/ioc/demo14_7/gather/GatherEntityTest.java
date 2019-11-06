/**
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.spring.ioc.demo14_7.gather;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author chenhx
 * @version GatherEntityTest.java, v 0.1 2019-02-12 22:29 chenhx
 */
public class GatherEntityTest {

    /**
     * 演示setter注入简单值和集合
     *
     * @throws Exception
     */
    @Test
    public void testGetEntity() throws Exception {
        //加载配置文件
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-ioc-gather.xml");
        //从Spring容器中获取需要的Bean
        GatherEntity gatherEntity = applicationContext.getBean("gatherEntity", GatherEntity.class);
        //输入Bean，记得添加toString方法
        System.out.println(gatherEntity);
    }

}

