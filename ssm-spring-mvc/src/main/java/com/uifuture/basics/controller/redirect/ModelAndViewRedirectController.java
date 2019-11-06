/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.basics.controller.redirect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 使用ModelAndView实现重定向
 * 7.1.6
 * @author chenhx
 * @version ModelAndViewRedirectController.java, v 0.1 2018-08-17 上午 12:26
 */
@Controller
@RequestMapping("modelAndViewRedirect")
public class ModelAndViewRedirectController {
    private Logger logger = LoggerFactory.getLogger(ModelAndViewRedirectController.class);

    /**
     * 返回ModelAndView
     *
     * @return
     */
    @RequestMapping(value = "/login")
    public ModelAndView login(String username) {
        logger.info("login...username={}", username);
        ModelAndView modelAndView = new ModelAndView("/redirect/login");
        modelAndView.addObject("username", username);
        return modelAndView;
    }

    /**
     * 使用绝对路径
     *
     * @param username
     * @return
     */
    @RequestMapping(value = "/testRedirect")
    public ModelAndView testRedirect(String username) {
        logger.info("testRedirect...username={}", username);
        return new ModelAndView("redirect:/modelAndViewRedirect/login?username=" + username);
    }

    /**
     * 不带"/",也就是前面的路径匹配类路径。
     *
     * @param username
     * @return
     */
    @RequestMapping(value = "/testRedirect2")
    public ModelAndView testRedirect2(String username) {
        logger.info("testRedirect2...username={}", username);
        ModelAndView modelAndView = new ModelAndView("redirect:login");
        //重定向中使用该种方式传值，相当于在URL链接后拼接参数和值进行传递。
        //不建议使用拼接的方式进行传值，可能会出现乱码问题，以及可能出现数据泄露的问题
        modelAndView.addObject("username", "testRedirect");
        return modelAndView;
    }

    /**
     * 演示重定向传递参数
     * ModelAttribute注解中的name（参数名称）必须填写，负责获取重定向传递的参数。
     * @param name
     * @return
     */
    @RequestMapping(value = "/login3")
    public ModelAndView login3(@ModelAttribute("username") String name,
                               @ModelAttribute("password") String password) {
        logger.info("login3...username={},password={}", name, password);
        ModelAndView modelAndView = new ModelAndView("/redirect/login");
        modelAndView.addObject("username", name);
        modelAndView.addObject("password", password);
        return modelAndView;
    }

    /**
     * 演示使用ModelAttribute注解来负责接收参数
     * 并进行使用addAttribute和addFlashAttribute方式的不同
     * @param attributes
     * @param username
     * @return
     */
    @RequestMapping(value = "/testRedirect3")
    public ModelAndView testRedirect3(RedirectAttributes attributes, String username) {
        logger.info("testRedirect3...username={}", username);
        //使用addAttribute方式，也是在URL后面拼接参数
        attributes.addAttribute("username", username);
        //使用addFlashAttribute，也就是将参数存到了FlashMap中，也就是我们前面6.9所介绍的重定向管理有关。
        //参数先保存在Session中，等待下一次请求访问，缓存时间180秒，并且在访问后会进行删除。
        attributes.addFlashAttribute("password", "1234");
        return new ModelAndView("redirect:/modelAndViewRedirect/login3");
    }

    /**
     * 不在方法参数上进行绑定参数，使用RequestContextUtils类的getInputFlashMap方法传递request进行获取传递的参数
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/login4")
    public ModelAndView login4(HttpServletRequest request) {
        Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
        logger.info("login4...map={}", map);
        ModelAndView modelAndView = new ModelAndView("/redirect/login");
        modelAndView.addObject("username", map.get("username"));
        return modelAndView;
    }

    /**
     * 演示使用RequestContextUtils进行获取传递过去的参数值
     * @param attributes
     * @param username
     * @return
     */
    @RequestMapping(value = "/testRedirect4")
    public ModelAndView testRedirect4(RedirectAttributes attributes, String username) {
        logger.info("testRedirect4...username={}", username);
        attributes.addFlashAttribute("username", username);
        return new ModelAndView("redirect:/modelAndViewRedirect/login4");
    }

}