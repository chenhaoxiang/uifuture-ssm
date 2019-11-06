package com.uifuture.ssm.spring.ioc.demo14_7.service;

import com.uifuture.ssm.spring.ioc.demo14_7.config.BeanConfiguration;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class SpringIoCServiceTest {

    /**
     * 测试 ClassPathXmlApplicationContext 加载配置文件
     *
     * @throws Exception
     */
    @Test
    public void testByXml() throws Exception {
        //加载配置文件
//        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring-ioc.xml");
//        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring-ioc.xml");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:spring-ioc*.xml");

        SpringIoCService springIoCService = applicationContext.getBean("springIoCServiceImpl", SpringIoCService.class);
        //多次获取并不会创建多个springIoCService对象,因为spring默认创建是单实例的作用域
//        SpringIoCService springIoCService= (SpringIoCService) applicationContext.getBean("springIoCServiceImpl");
        springIoCService.saySpringIoC();

    }

    /**
     * 测试通过FileSystemXmlApplicationContext加载配置文件
     */
    @Test
    public void testBySystemXml() {
        //默认为项目工作路径 即项目的根目录
//        ApplicationContext applicationContext = new FileSystemXmlApplicationContext("/src/main/resources/spring-ioc.xml");
        //也可以读取classpath下的文件
//        ApplicationContext applicationContext=new FileSystemXmlApplicationContext("classpath:spring-ioc.xml");
        //使用前缀file 表示的是文件的绝对路径
        ApplicationContext applicationContext = new FileSystemXmlApplicationContext("file:/Users/chenhx/Desktop/github/uifuture-ssm/ssm-spring-ioc-demo/src/main/resources/spring-ioc.xml");
        //多文件与ClassPathXmlApplicationContext相同
        SpringIoCService springIoCService = applicationContext.getBean("springIoCServiceImpl", SpringIoCService.class);
        springIoCService.saySpringIoC();

    }


    /**
     * 通过注解方式演示IoC注入Bean和获取Bean
     */
    @Test
    public void testByAnnotation() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfiguration.class);
        //名称必须BeanConfiguration中工程方法名称一致
        SpringIoCService springIoCService = applicationContext.getBean("springIoCService", SpringIoCService.class);
        springIoCService.saySpringIoC();
    }

}

