package com.uifuture.ssm.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2017/3/25.
 * Time: 上午 5:11.
 * Explain:Session的工具类
 */
public class SessionUtils {
    /**
     * 添加session
     *
     * @param request
     * @param sessionName
     * @param object
     */
    public static void setAttribute(HttpServletRequest request, String sessionName, Object object) {
        request.getSession().setAttribute(sessionName, object);
    }

    /**
     * 取得session
     *
     * @param request
     * @param sessionName session名称
     * @return
     */
    public static <T> T getAttribute(HttpServletRequest request, String sessionName) {
        return (T) request.getSession().getAttribute(sessionName);
    }

    /**
     * 删除sessionAttribute
     *
     * @param request
     * @param sessionName session名称
     */
    public static void removeAttribute(HttpServletRequest request, String sessionName) {
        request.getSession().removeAttribute(sessionName);
    }

    /**
     * 注销用户，使session失效。
     *
     * @param request
     */
    public static void removesession(HttpServletRequest request) {
        request.getSession().invalidate();
    }

    /**
     * 获取ip地址
     *
     * @param request
     * @return
     */
    public static String getIp(HttpServletRequest request) {
        return request.getRemoteAddr();
    }

}
