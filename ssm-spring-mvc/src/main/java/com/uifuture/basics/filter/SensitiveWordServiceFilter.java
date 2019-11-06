/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.basics.filter;

import com.uifuture.basics.service.SensitiveWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 注入Spring容器中的Bean来实现敏感词过滤
 * Configuration（Component）注解不要忘了.在ServletContext中无需再注册了。交由Spring容器进行管理该Filter的生命周期
 *
 * @author chenhx
 * @version SensitiveWordServiceFilter.java, v 0.1 2018-08-30 下午 8:36
 */
@Configuration
public class SensitiveWordServiceFilter extends OncePerRequestFilter {
    /**
     * 分隔符，建议可以弄复杂一点
     */
    private final static String SEPARATOR = "#-#=#&";
    /**
     * 注入Spring中的Bean
     */
    @Autowired
    private SensitiveWordService sensitiveWordService;

    /**
     * 保证每个请求只会被调用一次
     *
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //在Spring MVC的DispatcherServlet之前执行
        //在过滤器中用装饰模式把 原装request的功能增强了
        //拦截调用HttpServletRequest 的getParamter方法和getParameterValues方法
        SensitiveWordServiceRequest req = new SensitiveWordServiceRequest(request);
        //放行
        filterChain.doFilter(req, response);
    }

    /**
     * 对HttpServletRequest对象进行增强
     */
    class SensitiveWordServiceRequest extends HttpServletRequestWrapper {
        public SensitiveWordServiceRequest(HttpServletRequest request) {
            super(request);
        }

        /**
         * 将敏感词过滤
         * 在这里进行模拟敏感词已经获取了，建议从数据库或者文件中获取敏感词
         *
         * @param name
         * @return
         */
        @Override
        public String getParameter(String name) {
            String str = super.getParameter(name);
            if (str == null) {
                return str;
            }
            List<String> list = sensitiveWordService.selectAllSensitiveWord();
            for (String word : list) {
                str = str.replaceAll(word, "*");
            }
            return str;
        }

        /**
         * 该方法在Spring MVC中被调用，主要用作在Controller层的方法参数绑定上。通过该方法获取所有参数再进行绑定到方法参数上
         * 在该方法中将敏感词过滤后，即使是通过Spring MVC绑定到方法参数上的值也会被过滤
         *
         * @param name
         * @return
         */
        @Override
        public String[] getParameterValues(String name) {
            String[] strs = super.getParameterValues(name);
            List<String> list = sensitiveWordService.selectAllSensitiveWord();
            StringBuffer allStrs = new StringBuffer(strs[0]);
            //提高过滤的效率，否则假设参数值有20个，待过滤的词有1w个，那么如果通过两层循环过滤则需要10*1w次
            //另外，建议使用DFA算法进行敏感词的过滤。本项目使用的敏感词过滤性能待检测，而且缺点非常明显，那就是需要分隔符
            for (int i = 1; i < strs.length; i++) {
                allStrs.append(SEPARATOR + strs[i]);
            }
            for (String word : list) {
                allStrs = new StringBuffer(allStrs.toString().replaceAll(word, "*"));
            }
            return allStrs.toString().split(SEPARATOR);
        }
    }
}