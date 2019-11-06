/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.basics.controller.redirect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * 通过RedirectView对象实现重定向
 * 7.1.7
 *
 * @author chenhx
 * @version RedirectViewRedirectController.java, v 0.1 2018-08-18 下午 2:08
 */
@Controller
@RequestMapping("redirectViewRedirect")
public class RedirectViewRedirectController {
    private Logger logger = LoggerFactory.getLogger(RedirectViewRedirectController.class);

    /**
     * 仅仅作为展示的返回页面
     *
     * @return
     */
    @RequestMapping(value = "/login")
    public ModelAndView login() {
        logger.info("login...");
        return new ModelAndView("/redirect/login");
    }

    /**
     * 通过RedirectView实现重定向
     * 使用绝对路径进行重定向
     *
     * @return
     */
    @RequestMapping(value = "/redirect")
    public RedirectView redirect() {
        logger.info("redirect...");
        return new RedirectView("/redirectViewRedirect/login");
    }

    /**
     * 使用相对路径进行重定向
     *
     * @return
     */
    @RequestMapping(value = "/redirect1")
    public RedirectView redirect1() {
        logger.info("redirect1...");
        return new RedirectView("login");
    }

    /**
     * 使用带"/"的相对路径访问。设置contextRelative为true
     *
     * @return
     */
    @RequestMapping(value = "/redirect2")
    public RedirectView redirect2() {
        logger.info("redirect2...");
        return new RedirectView("/redirectViewRedirect/login", true);
    }

    /**
     * 使用相对路径进行重定向
     * 在这里设置contextRelative是没用的
     *
     * @return
     */
    @RequestMapping(value = "/redirect3")
    public RedirectView redirect3() {
        logger.info("redirect3...");
        return new RedirectView("login", true);
    }

}