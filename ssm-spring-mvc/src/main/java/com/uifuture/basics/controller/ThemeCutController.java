/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.basics.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.theme.SessionThemeResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 使用SessionThemeResolver进行主题的切换
 *
 * @author chenhx
 * @version IndexController.java, v 0.1 2018-08-21 下午 10:22
 */
@Controller
public class ThemeCutController {
    private static Logger logger = LoggerFactory.getLogger(ThemeCutController.class);
    @Autowired
    private SessionThemeResolver sessionThemeResolver;

    /**
     * 通过主题名称，进行主题的切换
     *
     * @return
     */
    @RequestMapping("/themeCut/{name}")
    public ModelAndView themeCut(HttpServletRequest request,
                                 HttpServletResponse response,
                                 @PathVariable("name") String name) {
        logger.info("通过主题名称，进行主题的切换,themeName={}", name);
        sessionThemeResolver.setThemeName(request, response, name);
        return new ModelAndView("theme");
    }
}