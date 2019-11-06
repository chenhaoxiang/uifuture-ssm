/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.basics.config;

import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;

/**
 * 配置静态资源的版本号
 * 章节:7.2.1
 *
 * @author chenhx
 * @version StaticResourcesVersion.java, v 0.1 2018-08-18 下午 6:15
 */
@Component
public class StaticResourcesVersion {
    /**
     * 版本号
     */
    private static String version = "1.0.1";
    /**
     * 全局版本号名称
     */
    private static String name = "version";

    /**
     * 设置全局变量，使用EL表达式即可获取版本号
     *
     * @param servletContext
     */
    public StaticResourcesVersion(ServletContext servletContext) {
        servletContext.setAttribute(name, name + "=" + version);
    }

    /**
     * Getter method for property <tt>version</tt>.
     *
     * @return property value of version
     */
    public static String getVersion() {
        return version;
    }

    /**
     * Getter method for property <tt>name</tt>.
     *
     * @return property value of name
     */
    public static String getName() {
        return name;
    }
}