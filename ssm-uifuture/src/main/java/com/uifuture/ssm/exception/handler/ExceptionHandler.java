/**
 * copyfuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.ssm.exception.handler;


import com.uifuture.ssm.base.BaseController;
import com.uifuture.ssm.base.BaseException;
import com.uifuture.ssm.enums.ResultCodeEnum;
import com.uifuture.ssm.exception.CheckoutException;
import com.uifuture.ssm.exception.CommonException;
import com.uifuture.ssm.exception.ServiceException;
import com.uifuture.ssm.result.ResultModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 异常捕获类
 *
 * @author chenhx
 * @version ExceptionHandler.java, v 0.1 2019-09-16 下午 3:41
 */
@Slf4j
@Configuration
public class ExceptionHandler extends BaseController implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ResultModel result;
        if (ex instanceof ServiceException) {
            //业务失败的异常
            log.error("{}{}", "[业务异常]", ex.getMessage());
            ServiceException exception = (ServiceException) ex;
            result = ResultModel.resultModel(exception.getCode(), ex.getMessage());
        } else if (ex instanceof NoHandlerFoundException) {
            result = ResultModel.resultModel(ResultCodeEnum.NOT_FOUND.getValue(), "接口 [" + request.getRequestURI() + "] 不存在");
        } else if (ex instanceof CommonException) {
            log.error("{}{}", "[公共异常]", ex.getMessage());
            CommonException exception = (CommonException) ex;
            result = ResultModel.resultModel(exception.getCode(), exception.getMessage());
        }  else if (ex instanceof CheckoutException) {
            log.error("{}{}", "[校验异常]", ex.getMessage());
            CheckoutException exception = (CheckoutException) ex;
            result = ResultModel.resultModel(exception.getCode(), exception.getMessage());
        } else if (ex instanceof MaxUploadSizeExceededException) {
            log.error("{}{}", "[上传文件过大]", ex.getMessage());
            MaxUploadSizeExceededException exception = (MaxUploadSizeExceededException) ex;
            result = ResultModel.resultModel(500, exception.getMessage());
        } else if (ex instanceof BaseException) {
            log.error("{}{}", "[基础异常]", ex.getMessage());
            BaseException exception = (BaseException) ex;
            result = ResultModel.resultModel(500, exception.getMessage());
        } else {
            result = ResultModel.resultModel(ResultCodeEnum.INTERNAL_SERVER_ERROR.getValue(), "接口 [" + request.getRequestURI() + "] 内部错误，请联系客服，邮箱：uifuture@uifuture.com");
            String message;
            if (handler instanceof HandlerMethod) {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常信息：%s",
                        request.getRequestURI(),
                        handlerMethod.getBean().getClass().getName(),
                        handlerMethod.getMethod().getName(),
                        ex.getMessage());
            } else {
                message = ex.getMessage();
            }
            //增加url异常输出
            String url = request.getRequestURI();
            Map map = request.getParameterMap();
            log.error("异常链接URL:" + url + "，请求参数:" + map + "，请求IP:" + getIpAddress(request) + "，异常信息：" + message, ex);
        }
        responseResult(response, result);
        return null;
    }
}
