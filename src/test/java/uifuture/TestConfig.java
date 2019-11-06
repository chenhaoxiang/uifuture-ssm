/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package uifuture;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author chenhx
 * @version TestConfig.java, v 0.1 2018-07-01 下午 3:35
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring.xml", "classpath*:spring-mybatis.xml"})
public class TestConfig {
    private Logger logger = Logger.getLogger(TestConfig.class);

    @org.junit.Test
    public void testPass() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        DruidDataSource druidDataSource = (DruidDataSource) context.getBean("dataSource");
        logger.debug("解密后的账号：" + druidDataSource.getUsername());
        logger.debug("解密后的密码：" + druidDataSource.getPassword());
    }
}