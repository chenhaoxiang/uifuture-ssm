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

import javax.servlet.http.HttpServletRequest;

/**
 * 演示通过ModelAndView进行请求转发
 * 7.1.3
 * @author chenhx
 * @version ModelAndViewForwardController.java, v 0.1 2018-08-16 下午 8:09
 */
@Controller
@RequestMapping("forward")
public class ModelAndViewForwardController {
    private Logger logger = LoggerFactory.getLogger(ModelAndViewForwardController.class);

    /**
     * 返回ModelAndView
     *
     * @return
     */
    @RequestMapping(value = "/login")
    public ModelAndView login2(HttpServletRequest request, @RequestParam String username) {
        logger.info("login...request.getAttribute(\"username\")={},username={},request.getParameter(\"username\")={}", request.getAttribute("username"), username, request.getParameter("username"));
        ModelAndView modelAndView = new ModelAndView("/forward/login");
        modelAndView.addObject("username", request.getParameter("username"));
        return modelAndView;
    }

    /**
     * 进行转发到login方法
     * /代表着使用绝对路径访问，在同一个Web容器中
     * 使用HttpServletRequest设置值进行传递
     *
     * @return
     */
    @RequestMapping(value = "/testForward")
    public ModelAndView testForward(HttpServletRequest request, String username) {
        logger.info("testForward...username={},request.getAttribute(\"username\")={},request.getParameter(\"username\")={}", username, request.getAttribute("username"), request.getParameter("username"));
        //使用此种方式虽然能够传递值到下一个方法中。但是注意，如果使用了转发。
        // 那么这里HttpServletRequest设置的参数是无法通过RequestParam注解获取到该方法中设置的Attribute的
        //同样在下个方法中需要使用HttpServletRequest的getAttribute方法进行获取。
        request.setAttribute("username", "springmvc");
        ModelAndView model = new ModelAndView("forward:/forward/login");
        return model;
    }

    /**
     * 使用ModelAndView设置值进行传递
     *
     * @return
     */
    @RequestMapping(value = "/testForward2")
    public ModelAndView testForward2(String username) {
        logger.info("testForward2...username={}", username);
        ModelAndView model = new ModelAndView("forward:login");
        //使用此种方式也是可以将值传递到下一个请求方法中。
        //实际就是Springm MVC内部使用ModelMap（本质上就是HashMap）进行维护key-value。简单的理解为request.setAttribute即可。建议使用该种方式
        model.addObject("username", "springmvc");
        return model;
    }
}