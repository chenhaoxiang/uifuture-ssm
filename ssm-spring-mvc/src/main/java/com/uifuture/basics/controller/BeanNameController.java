/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.basics.controller;

import com.uifuture.basics.form.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 通过定义的 beanName 进行查找要请求的Controller
 *
 * @author chenhx
 * @version BeanNameController.java, v 0.1 2018-08-09 下午 9:05
 */
@Controller("/beanName")
public class BeanNameController extends AbstractController {
    /**
     * 演示使用BeanNameUrlHandlerMapping映射处理器进行处理
     *
     * @return
     */
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User userInfo = new User();
        userInfo.setName("springmvc");
        userInfo.setAge(21);
        return new ModelAndView("userInfo", "user", userInfo);
    }
}