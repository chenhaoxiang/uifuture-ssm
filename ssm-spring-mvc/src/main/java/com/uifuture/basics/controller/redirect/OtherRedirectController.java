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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 演示一下通过字符串实现重定向
 * 以及使用HttpServletResponse对象的sendRedirect方法实现重定向
 * 7.1.5
 *
 * @author chenhx
 * @version OtherRedirectController.java, v 0.1 2018-08-17 下午 9:14
 */
@Controller
@RequestMapping("otherRedirect")
public class OtherRedirectController {

    private Logger logger = LoggerFactory.getLogger(OtherRedirectController.class);

    /**
     * 仅仅作为展示的返回页面
     *
     * @return
     */
    @RequestMapping(value = "/login")
    public ModelAndView login() {
        logger.info("OtherRedirectController.login...");
        return new ModelAndView("/redirect/login");
    }

    /**
     * 通过字符串实现重定向
     *
     * @return
     */
    @RequestMapping(value = "/stringRedirect")
    public String stringRedirect() {
        logger.info("stringRedirect...");
        return "redirect:/otherRedirect/login";
    }

    /**
     * 使用HttpServletResponse对象的sendRedirect方法实现重定向
     *
     * @return
     */
    @RequestMapping(value = "/sendRedirect")
    public void sendRedirect(HttpServletResponse response) throws IOException {
        logger.info("sendRedirect...");
        response.sendRedirect("/otherRedirect/login");
    }

    /**
     * 使用HttpServletResponse对象的sendRedirect方法实现重定向
     *
     * @return
     */
    @RequestMapping(value = "/sendRedirect2")
    public void sendRedirect2(HttpServletResponse response) throws IOException {
        logger.info("sendRedirect2...");
        response.sendRedirect("login");
    }
}