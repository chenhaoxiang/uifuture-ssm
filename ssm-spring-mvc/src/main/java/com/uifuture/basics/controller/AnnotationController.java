/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.basics.controller;

import com.uifuture.basics.form.User;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * SpringMVC中常用注解的演示
 *
 * @author chenhx
 * @version AnnotationController.java, v 0.1 2018-08-05 下午 4:43
 */
@Controller
@RequestMapping("annotation")
public class AnnotationController {
    /**
     * 可以通过 http://localhost:8080/annotation/aaa/test 访问
     * 无法通过http://localhost:8080/annotation/test 访问
     *
     * @return
     */
    @RequestMapping("*/test")
    @ResponseBody
    public String test() {
        System.out.println("-----AnnotationController.test-----");
        return "test";
    }

    /**
     * 可以通过 http://localhost:8080/annotation/test2 访问
     * 和http://localhost:8080/annotation/aaa/test2 访问
     *
     * @return
     */
    @RequestMapping({"**/test2", "test3"})
    @ResponseBody
    public String test2() {
        System.out.println("-----AnnotationController.test2-----");
        return "test2";
    }

    /**
     * produces = MediaType.APPLICATION_JSON_UTF8_VALUE - 防止返回数据乱码
     * ResponseBody注解返回的默认编码为ISO-8859-1
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/testRequestBody"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public User testRequestBody(@RequestBody User user) {
        System.out.println("-----user=" + user);
        return user;
    }

    /**
     * 演示PathVariable注解
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "/{str}")
    public @ResponseBody
    String testPathVariable(@PathVariable("str") String name) {
        return name;
    }

    @RequestMapping("/testCookieValue")
    @ResponseBody
    public String testCookieValue(@CookieValue("JSESSIONID") String jsessionId) {
        System.out.println("jsessionId=" + jsessionId);
        return jsessionId;
    }

    @RequestMapping("/testRequestParam")
    @ResponseBody
    public String testRequestParam(@RequestParam(value = "name", defaultValue = "springmvc") String username) {
        System.out.println("usernam=" + username);
        return username;
    }


    /**
     * 该参数会在InitBinder注解的方法中进行转换
     * 将string类型，例如2018-08-05 20:19:20 会解析成Date对象
     *
     * @param date
     * @return
     */
    @GetMapping("/testDate")
    @ResponseBody
    public String testDate(@RequestParam("data") Date date) {
        System.out.println("--------------data=" + date);
        return "data:" + date;
    }

    /**
     * InitBinder注解用于Date类型的转换
     * ServletRequestDataBinder类是WebDataBinder的子类
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //指定日期/时间解析规则是否宽松
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * 对象转换成json格式数据返回到前端
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/testUserToJson"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public User testUserToJson(@RequestBody User user) {
        System.out.println("-----user=" + user);
        return user;
    }

    /**
     * 对象转换成xml格式数据返回到前端
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/testUserToXml"
            , produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public User testUserToXml(@RequestBody User user) {
        System.out.println("-----user=" + user);
        return user;
    }

    /**
     * 测试异常处理
     *
     * @return
     */
    @RequestMapping(value = "/errorDemo")
    @ResponseBody
    public String errorDemo() {
        System.out.println("异常处理演示");
        throw new RuntimeException("测试运行时异常处理");
    }
}