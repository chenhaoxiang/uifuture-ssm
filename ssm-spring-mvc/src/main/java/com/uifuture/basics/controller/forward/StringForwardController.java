/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.basics.controller.forward;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 演示通过ViewResolver进行请求转发
 * 7.1.5
 * @author chenhx
 * @version ForwardController.java, v 0.1 2018-08-15 下午 8:41
 */
@Controller
@RequestMapping("stringForward")
public class StringForwardController {
    private Logger logger = LoggerFactory.getLogger(StringForwardController.class);

    /**
     * 返回ModelAndView
     *
     * @return
     */
    @RequestMapping(value = "/login")
    public ModelAndView login(String username) {
        logger.info("login...username={}", username);
        ModelAndView modelAndView = new ModelAndView("forward/login");
        //映射到页面，有没有斜杠都是一样的,就和RequestMapping注解中路径最前面，有没有斜杠都行
//        ModelAndView modelAndView = new ModelAndView("/forward/login");
        modelAndView.addObject("username", username);
        return modelAndView;
    }

    /**
     * 进行转发到login方法
     * /代表着使用绝对路径访问，在同一个项目中
     *
     * @return
     */
    @RequestMapping(value = "/backslashForward")
    public String backslashForward() {
        logger.info("backslashForward...");
        return "forward:/stringForward/login";
    }

    /**
     * 进行转发到login方法
     * 由于在一个Controller中，可以不使用/，代表相对路径访问
     * 转发会自动携带请求中的参数进行转发
     * 也就是同一个request在服务器内部进行转发
     *
     * @return
     */
    @RequestMapping(value = "/parameterForward")
    public String parameterForward(String username) {
        logger.info("parameterForward...username={}", username);
        return "forward:login";
    }
}