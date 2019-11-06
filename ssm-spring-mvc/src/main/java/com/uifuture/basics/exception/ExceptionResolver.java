/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.basics.exception;

import com.uifuture.basics.commons.ResultModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理类
 * 实现HandlerExceptionResolver接口
 * 只处理程序运行过程中的异常
 * 章节:8.1.1
 *
 * @author chenhx
 * @version ExceptionResolver.java, v 0.1 2018-08-22 下午 9:05
 */
public class ExceptionResolver implements HandlerExceptionResolver {
    private static Logger logger = LoggerFactory.getLogger(ExceptionResolver.class);

    /**
     * 拦截所有的异常
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @return
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logger.error("异常信息:{}", ex.getMessage());
        Map<String, Object> model = new HashMap<>(4);
        model.put("data", ResultModel.error(ex.getMessage()));
        // 根据不同异常转向不同页面（建议自定义异常）
        //选择的这两个异常均是继承RuntimeException异常类的
        if (ex instanceof HttpMessageNotWritableException) {
            return new ModelAndView("error/httpMessageNotWritableException", model);
        } else if (ex instanceof HttpMessageNotReadableException) {
            return new ModelAndView("error/httpMessageNotReadableException", model);
        }
        return new ModelAndView("error/otherError", model);
    }
}