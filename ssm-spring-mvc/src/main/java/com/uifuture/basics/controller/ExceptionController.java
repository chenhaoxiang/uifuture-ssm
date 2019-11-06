/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.basics.controller;

import com.uifuture.basics.commons.ResultModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 演示异常的抛出Controller
 * 章节:8.1
 * @author chenhx
 * @version ExceptionController.java, v 0.1 2018-08-22 下午 9:29
 */
@Controller
@RequestMapping("/exception")
public class ExceptionController {
    private Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    /**
     * 配置拦截本类中HttpMessageNotReadableException异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ModelAndView httpMessageNotReadableExceptionHandler(HttpServletRequest request, HttpMessageNotReadableException ex) {
        logger.error("异常信息:{}", ex.getMessage());
        Map<String, Object> model = new HashMap<>(4);
        model.put("data", ResultModel.error("[ExceptionHandler]" + ex.getMessage()));
        return new ModelAndView("error/httpMessageNotReadableException", model);
    }

    /**
     * 根据传进来的code不同，抛出不同的运行时异常
     *
     * @param code
     * @return
     */
    @RequestMapping("/code/{code}")
    @ResponseBody
    public String exception(@PathVariable("code") Integer code) {
        logger.info("code={}", code);
        if (code.equals(1)) {
            throw new HttpMessageNotWritableException("code等于1，抛出BindException异常");
        } else if (code.equals(2)) {
            throw new HttpMessageNotReadableException("code等于2，抛出HttpMessageNotReadableException异常");
        } else if (code.equals(3)) {
            throw new RuntimeException("code等于3，其他的运行时异常");
        }
        return "success";
    }
}