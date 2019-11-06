/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.basics.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import java.util.EnumSet;

/**
 * Web配置
 *
 * @author chenhx
 * @version WebInitializer.java, v 0.1 2018-08-04 下午 7:08
 */
public class WebInitializer implements WebApplicationInitializer {
    /**
     * 基于Java的初始化器（可定义任意数量）
     * 例如servlet、过滤器等等
     *
     * @param servletContext
     */
    @Override
    public void onStartup(ServletContext servletContext) {
        //一、与xml文件组合使用
//        XmlWebApplicationContext appContext = new XmlWebApplicationContext();
//        appContext.setConfigLocation("/WEB-INF/classes/spring-mvc-servlet.xml");

        //二、使用编程式方式代码配置
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(MvcConfig.class);
        //新建WebApplicationContext,注册配置类，并将其和当前servletContext关联
        appContext.setServletContext(servletContext);

        //注册并映射调度程序servlet
        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher",
                new DispatcherServlet(appContext));
        //注册Spring MVC的DispatcherServlet，替代web.xml的使用
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);

        //注册Filter  字符编码的过滤器
        FilterRegistration.Dynamic dynamic = servletContext.addFilter("characterEncodingFilter",
                new CharacterEncodingFilter("UTF-8", true, true));
        //映射Filter
        dynamic.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");

//        //注册Filter,敏感字过滤器
//        FilterRegistration.Dynamic sensitiveWordDynamic = servletContext.addFilter("sensitiveWordFilter", new SensitiveWordFilter());
//        //映射Filter
//        sensitiveWordDynamic.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");

        //注册DelegatingFilterProxy,并且设置targetBeanName的值为sensitiveWordServiceFilter
        DelegatingFilterProxy delegatingFilterProxy = new DelegatingFilterProxy("sensitiveWordServiceFilter");
        //配置此参数为true将此filter的生命周期交由server容器管理，包括执行其init，destroy方法
        delegatingFilterProxy.setTargetFilterLifecycle(true);
        FilterRegistration.Dynamic filterRegistration = servletContext.addFilter("delegatingFilterProxy", delegatingFilterProxy);
        //映射Filter
        filterRegistration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
    }
}