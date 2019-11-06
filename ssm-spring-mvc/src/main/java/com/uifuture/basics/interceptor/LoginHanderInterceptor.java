/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.basics.interceptor;

import com.uifuture.basics.form.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 *
 * @author chenhx
 * @version LoginHanderInterceptor.java, v 0.1 2018-09-01 下午 2:42
 */
public class LoginHanderInterceptor implements HandlerInterceptor {
    /**
     * 一般情况下，我们只会重写该方法
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断用户是否登录
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            //用户尚未登录
//            return false;
        }
        return true;
    }
}