/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.controller;

import com.uifuture.ssm.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 测试
 *
 * @author chenhx
 * @version TestController.java, v 0.1 2019-09-14 00:25 chenhx
 */
@RequestMapping("/testController")
@Slf4j
@Controller
public class TestController extends BaseController {

    @RequestMapping("index")
    public ModelAndView index() {
        log.info("访问testController/index");
        return new ModelAndView("index");
    }

}

