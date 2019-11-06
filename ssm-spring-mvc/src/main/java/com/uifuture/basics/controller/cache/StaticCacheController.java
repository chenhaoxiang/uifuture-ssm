/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.basics.controller.cache;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 静态资源的缓存演示
 * 章节：7.2.2
 *
 * @author chenhx
 * @version StaticCacheController.java, v 0.1 2018-08-18 下午 5:01
 */
@Controller
@RequestMapping("cache")
public class StaticCacheController {
    /**
     * 进行跳转到jsp页面，里面进行加载了静态资源
     *
     * @return
     */
    @RequestMapping("index")
    public ModelAndView cache() {
        return new ModelAndView("/cache/index");
    }
}