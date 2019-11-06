/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.basics.controller;

import com.uifuture.basics.form.User;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;

/**
 * 文件上传和下载的Controller
 * 章节:7.3
 * @author chenhx
 * @version FileUploadController.java, v 0.1 2018-08-19 下午 6:25
 */
@Controller
@RequestMapping("file")
public class FileController {
    private Logger logger = LoggerFactory.getLogger(FileController.class);

    @PostMapping(value = "/upload")
    public ModelAndView register(HttpServletRequest request,
                                 User user, MultipartFile image) throws Exception {
        ModelAndView modelAndView = new ModelAndView("/upload/message");
        if (user == null || image == null) {
            modelAndView.addObject("message", "参数错误");
            return modelAndView;
        }
        logger.info("参数，user={},imageName={}", user, image.getOriginalFilename());
        // 上传的绝对路径
        //例如该项目中，路径为 ***/项目名/classes/artifacts/ssm_spring_mvc_war_exploded/WEB-INF/file/images/，***为省略的项目路径
        //getRealPath方法返回服务器filesystem上的绝对路径。getServletContext().getRealPath是返回上下文所在根路径的绝对路径
        String path = request.getServletContext().getRealPath("/WEB-INF/file/images/");
        // 获取上传文件名
        String filename = image.getOriginalFilename();
        File filepath = new File(path, filename);
        logger.info("路径:" + filepath);
        // 判断路径是否存在，不存在就创建一个
        if (!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        }
        // 将文件保存到一个目标文件中  File.separator-文件分隔符
        image.transferTo(new File(path + File.separator + filename));
        modelAndView.addObject("message", "上传成功");
        return modelAndView;
    }

    /**
     * 如果出现大文件，会OOM。
     * java.lang.OutOfMemoryError: Java heap space
     *
     * @param request
     * @param filename
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/download")
    public ResponseEntity<byte[]> download(HttpServletRequest request,
                                           String filename) throws Exception {
        logger.info("filename={}", filename);
        //下载文件路径
        String path = request.getServletContext().getRealPath("/WEB-INF/file/images/");
        File file = new File(path + File.separator + filename);
        HttpHeaders headers = new HttpHeaders();
        //下载显示的文件名，解决中文名称乱码问题
        String downloadFielName = new String(filename.getBytes("UTF-8"), "iso-8859-1");
        //通知浏览器以attachment（下载方式）
        headers.setContentDispositionFormData("attachment", downloadFielName);
        //application/octet-stream ： 二进制流数据（最常见的文件下载）。
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }


    /**
     * 大文件下载
     *
     * @param filename
     * @param response
     * @param request
     */
    @RequestMapping("downloadLargeFile")
    public void downloadLargeFile(String filename, HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException {
        //设置响应头和客户端保存文件名
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        //设置response内容的类型  二进制流
        response.setContentType("application/octet-stream");
        String downloadFielName = new String(filename.getBytes("UTF-8"), "iso-8859-1");
        logger.info("filename={},downloadFielName={}", filename, downloadFielName);
        //设置头部信息
        response.setHeader("Content-Disposition", "attachment;fileName=" + downloadFielName);
        //下载文件路径
        String path = request.getServletContext().getRealPath("/WEB-INF/file/images/");
        File file = new File(path + File.separator + filename);
        //文件不存在
        if (!file.exists()) {
            return;
        }
        //设置文件的大小
        response.setContentLength((int) file.length());
        //利用BufferedInputStream从文件流读取数据。因为在读取大文件时，BufferedInputStream的速度比InputStream快很多
        BufferedInputStream inputStream = null;
        OutputStream os = null;
        try {
            //打开本地文件流
            inputStream = new BufferedInputStream(new FileInputStream(file));
            //激活下载操作
            os = new BufferedOutputStream(response.getOutputStream());

            //循环写入输出流 每次读取10MB，防止内存溢出
            byte[] b = new byte[1024 * 1024 * 10];
            int length;
            //遍历读取输入流与写到response的输出流中
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
            inputStream.close();
            // 这里注意关闭。一定要调用flush()方法
            os.flush();
            os.close();
        } catch (SocketException e) {
            logger.warn("用户取消了下载:" + e.getMessage());
        } catch (Exception e) {
            logger.error("下载文件出现异常:" + e.getMessage());
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                logger.error("关闭下载流出现异常:" + e.getMessage());
            }
        }
    }

}