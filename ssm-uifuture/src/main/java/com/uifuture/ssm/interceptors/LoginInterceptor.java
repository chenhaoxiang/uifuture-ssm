/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.interceptors;

import com.uifuture.ssm.base.BaseController;
import com.uifuture.ssm.entity.UsersEntity;
import com.uifuture.ssm.result.ResultModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器,必须登录才能访问
 *
 * @author chenhx
 * @version AutoLoginInterceptor.java, v 0.1 2019-09-17 23:03 chenhx
 */
@Slf4j
@Configuration
public class LoginInterceptor extends BaseController implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断是否登录
        UsersEntity users = getLoginInfo(request);
        //已经登录
        if (users == null) {
            //写入提醒
            ResultModel resultModel = new ResultModel(500, "登录后方可操作");
            responseResult(response, resultModel);
            return false;
        }
        return true;
    }
}
