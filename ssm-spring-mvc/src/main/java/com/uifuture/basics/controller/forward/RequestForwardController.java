/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.basics.controller.forward;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 使用HttpServletRequest进行转发
 * 7.1.4
 * @author chenhx
 * @version RequestForwardController.java, v 0.1 2018-08-17 上午 12:04
 */
@Controller
@RequestMapping("requestForward")
public class RequestForwardController {
    private Logger logger = LoggerFactory.getLogger(RequestForwardController.class);

    /**
     * 返回ModelAndView
     *
     * @return
     */
    @RequestMapping(value = "/login")
    public ModelAndView login(HttpServletRequest request, @RequestParam String username) {
        logger.info("RequestForwardController.login...request.getAttribute(\"username\")={},username={},request.getParameter(\"username\")={}", request.getAttribute("username"), username, request.getParameter("username"));
        ModelAndView modelAndView = new ModelAndView("/forward/login");
        modelAndView.addObject("username", request.getAttribute("username"));
        return modelAndView;
    }

    /**
     * 使用HttpServletRequest方式进行转发，需要HttpServletRequest和HttpServletResponse
     * 使用绝对路径进行转发,可以转发到不同路径的Controller类
     *
     * @param request
     * @param response
     * @param username
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/testForward")
    public void testForward(HttpServletRequest request, HttpServletResponse response, String username) throws ServletException, IOException {
        logger.info("testForward...username={}", username);
        request.getRequestDispatcher("/forward/login").forward(request, response);
    }

    /**
     * 使用相对路径进行转发，前缀匹配类上的路径
     *
     * @param request
     * @param response
     * @param username
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/testForward1")
    public void testForward4(HttpServletRequest request, HttpServletResponse response, String username) throws ServletException, IOException {
        logger.info("testForward1...username={}", username);
        request.getRequestDispatcher("login").forward(request, response);
    }
}