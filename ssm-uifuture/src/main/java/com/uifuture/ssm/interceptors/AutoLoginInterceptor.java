/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.interceptors;

import com.alibaba.fastjson.JSON;
import com.uifuture.ssm.base.BaseController;
import com.uifuture.ssm.common.UsersConstants;
import com.uifuture.ssm.dto.UsersCookieDTO;
import com.uifuture.ssm.entity.UsersEntity;
import com.uifuture.ssm.enums.DeleteEnum;
import com.uifuture.ssm.enums.UsersStateEnum;
import com.uifuture.ssm.service.UsersService;
import com.uifuture.ssm.util.CookieUtils;
import com.uifuture.ssm.util.DateUtils;
import com.uifuture.ssm.util.PasswordUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自动登录拦截器
 *
 * @author chenhx
 * @version AutoLoginInterceptor.java, v 0.1 2019-09-17 23:03 chenhx
 */
@Slf4j
@Configuration
public class AutoLoginInterceptor extends BaseController implements HandlerInterceptor {
    @Autowired
    private UsersService usersService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断是否登录
        UsersEntity users = getLoginInfo(request);
        if (users != null) {
            //已经登录
            return true;
        }
        //从cookie中获取
        String usersStr = CookieUtils.getCookie(request, UsersConstants.COOKIE_USERS_LOGIN_INFO);
        if (StringUtils.isEmpty(usersStr)) {
            return true;
        }
        try {
            UsersCookieDTO usersCookieDTO = JSON.parseObject(usersStr, UsersCookieDTO.class);
            //获取用户信息
            UsersEntity realUsers = usersService.selectByUsername(usersCookieDTO.getUsername());
            if (realUsers == null) {
                return true;
            }

            //用户是否被禁用，被删除
            if (UsersStateEnum.FORBIDDEN.getValue().equals(realUsers.getState())) {
                //删除cookie
                CookieUtils.delCookie(response, UsersConstants.COOKIE_USERS_LOGIN_INFO);
                return true;
            }
            if (!DeleteEnum.NO_DELETE.getValue().equals(realUsers.getDeleteTime())) {
                //删除cookie
                CookieUtils.delCookie(response, UsersConstants.COOKIE_USERS_LOGIN_INFO);
                return true;
            }

            //判断时间是否是30天内
            if (DateUtils.getLongDateTimeMS() - usersCookieDTO.getTime() > DAY30_MS) {
                //删除cookie
                CookieUtils.delCookie(response, UsersConstants.COOKIE_USERS_LOGIN_INFO);
                return true;
            }
            String token = PasswordUtils.getToken(realUsers.getSalt(), realUsers.getPassword(), usersCookieDTO.getTime());
            if (token.equals(usersCookieDTO.getToken())) {
                //进行登录
                setLoginInfo(request, realUsers);
            }
        } catch (Exception e) {
            log.error("转换成UsersCookieDTO出现异常,usersStr=" + usersStr, e);
        }
        return true;
    }
}
