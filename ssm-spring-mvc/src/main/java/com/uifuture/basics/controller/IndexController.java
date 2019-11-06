/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.basics.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller层演示
 *
 * @author chenhx
 * @version IndexController.java, v 0.1 2018-08-04 下午 7:17
 */
@Controller
public class IndexController {
    private static Logger logger = LoggerFactory.getLogger(IndexController.class);

    /**
     * 返回string，会映射到对应的页面
     *
     * @return
     */
    @RequestMapping({"/index", ""})
    public String index() {
        logger.info("访问首页");
        return "index";
    }

    /**
     * 使用ResponseBody注解，返回字符串
     *
     * @return
     */
    @RequestMapping("/info")
    @ResponseBody
    public String info() {
        logger.info("获取信息");
        return "username:chenhx";
    }

    /**
     * 测试RequestToViewNameTranslator
     *
     * @return
     */
    @RequestMapping("/testRequestToViewNameTranslator")
    public void testRequestToViewNameTranslator() {
        logger.info("testRequestToViewNameTranslator");
    }
}