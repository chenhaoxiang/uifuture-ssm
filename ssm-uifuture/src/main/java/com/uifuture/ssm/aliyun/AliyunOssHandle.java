/**
 * copyfuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.aliyun;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.uifuture.ssm.util.PasswordUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 阿里云资源操作类
 *
 * @author chenhx
 * @version AliyunOssHandle.java, v 0.1 2019-03-23 05:05 chenhx
 */
@Configuration
@Slf4j
public class AliyunOssHandle {
    private static OSSClient client;
    @Autowired
    private AliyunOssConfig aliyunOssConfig;

    /**
     * 修改文件名
     *
     * @param fileName 文件名
     * @return 文件的新名称
     */
    public static String getfileName(String fileName, String userName) {
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        return userName + "-" + PasswordUtils.randomStringLower(10) + fileType;

    }

    public OSSClient getClient() {
        if (client == null) {
            client = new OSSClient(aliyunOssConfig.getEndpoint(), aliyunOssConfig.getAccessKeyId(), aliyunOssConfig.getAccessKeySecret());
        }
        return client;
    }

    public Boolean folderExist(OSS client, String folderName) {
        return client.doesObjectExist(aliyunOssConfig.getBucketImgName(), folderName);
    }

    /**
     * 判断文件夹是否存在
     *
     * @param folderName
     * @return
     */
    public Boolean folderExist(String folderName) {
        return folderExist(getClient(), folderName);
    }

    public String createFolder(OSS client, String folderName) {
        try {
            //判断文件夹是否存在，不存在则创建
            if (!client.doesObjectExist(aliyunOssConfig.getBucketImgName(), folderName)) {
                //创建文件夹
                client.putObject(aliyunOssConfig.getBucketImgName(), folderName, new ByteArrayInputStream(new byte[0]));
                log.info("创建文件夹成功：{}", folderName);
                //得到文件夹名
                OSSObject object = client.getObject(aliyunOssConfig.getBucketImgName(), folderName);
                return object.getKey();
            }
            return folderName;
        } catch (Exception ce) {
            log.error("创建空文件夹到oss出现异常", ce);
        }
        return "";
    }

    /**
     * 创建文件夹到oss
     *
     * @param folderName 模拟文件夹名如"qj_nanjing/"
     * @return 文件夹名
     */
    public String createFolder(String folderName) {
        return createFolder(getClient(), folderName);
    }

    public void deleteFile(OSS ossClient, String bucketName, String folder, String key) {
        try {
            ossClient.deleteObject(bucketName, folder + key);
        } catch (Exception e) {
            log.error("根据key删除OSS服务器上的文件出现异常,folder=" + folder + ",key=" + key, e);
        }
        log.info("删除" + bucketName + "下的文件" + folder + key + "成功");
    }

    /**
     * 根据key删除OSS服务器上的文件
     *
     * @param folder 模拟文件夹名 如"qj_nanjing/"
     * @param key    Bucket下的文件的路径名+文件名 如："upload/cake.jpg"
     */
    public void deleteFile(String folder, String key) {
        OSS client = getClient();
        deleteFile(client, aliyunOssConfig.getBucketImgName(), folder, key);
    }

    /**
     * 上传图片至OSS
     *
     * @param file   上传文件（文件全路径如：D:\\image\\cake.jpg）
     * @param folder 模拟文件夹名 如"qj_nanjing/" 前不能有分隔符，最后需要有分隔符
     * @return String 返回的唯一MD5数字签名
     */
    public String uploadObject2OSS(File file, String folder) {
        return uploadObject2OSS(getClient(), file, aliyunOssConfig.getBucketImgName(), folder);
    }


    /**
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType
     *
     * @param fileName 文件名
     * @return 文件的contentType
     */
    public String getContentType(String fileName) {
        //文件的后缀名
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        if (".bmp".equalsIgnoreCase(fileExtension)) {
            return "image/bmp";
        }
        if (".gif".equalsIgnoreCase(fileExtension)) {
            return "image/gif";
        }
        if (".jpeg".equalsIgnoreCase(fileExtension) || ".jpg".equalsIgnoreCase(fileExtension) || ".png".equalsIgnoreCase(fileExtension)) {
            return "image/jpeg";
        }
        if (".html".equalsIgnoreCase(fileExtension)) {
            return "text/html";
        }
        if (".txt".equalsIgnoreCase(fileExtension)) {
            return "text/plain";
        }
        if (".vsd".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.visio";
        }
        if (".ppt".equalsIgnoreCase(fileExtension) || "pptx".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.ms-powerpoint";
        }
        if (".doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension)) {
            return "application/msword";
        }
        if (".xml".equalsIgnoreCase(fileExtension)) {
            return "text/xml";
        }
        //默认返回类型
        return "image/jpeg";
    }

    public String uploadObject2OSS(OSS ossClient, File file, String bucketName, String folder) {
        String resultStr = null;
        try {
            //以输入流的形式上传文件
            InputStream inputStream = new FileInputStream(file);
            //文件名
            String fileName = file.getName();
            //文件大小
            long fileSize = file.length();
            //创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            //上传的文件的长度
            metadata.setContentLength(fileSize);
            //指定该Object被下载时的网页的缓存行为 一年
            metadata.setCacheControl("max-age=31104000");
            //指定该Object下设置Header
//            metadata.setHeader("Pragma", "no-cache");
            //指定该Object被下载时的内容编码格式
            metadata.setContentEncoding("utf-8");
            //文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
            //如果没有扩展名则填默认值application/octet-stream
            metadata.setContentType(getContentType(fileName));
            //指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
            metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
            //上传文件   (上传文件流的形式)
            PutObjectResult putResult = ossClient.putObject(bucketName, folder + fileName, inputStream, metadata);
            //解析结果
            resultStr = putResult.getETag();
            log.info("上传文件至阿里云成功，文件名称:{},路径:{}", fileName, folder);
        } catch (Exception e) {
            log.error("上传阿里云OSS服务器异常." + e.getMessage() + ",folder=" + folder + ",fileName=" + file.getName(), e);
        }
        return resultStr;
    }

    /**
     * 上传图片至OSS
     *
     * @param in       文件流
     * @param name     获取文件后缀需要
     * @param fileSize 文件大小
     * @param folder   oss的路径
     * @return 图片的全路径
     */
    public String uploadObject2OSS(InputStream in, String name, Long fileSize, String folder, String fileName, String userName) {
        OSSClient ossClient = getClient();
        String resultStr = null;
        try {
            //判断folder
            createFolder(folder);

            //文件名  如果出现重复，则重新生成名字，再上传
            if (ossClient.doesObjectExist(aliyunOssConfig.getBucketImgName(), folder + fileName)) {
                fileName = getfileName(name, userName);
            }
            //创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            //上传的文件的长度
            metadata.setContentLength(fileSize);
            //指定该Object被下载时的网页的缓存行为 一年
            metadata.setCacheControl("max-age=31104000");
            //指定该Object下设置Header
//            metadata.setHeader("Pragma", "no-cache");
            //指定该Object被下载时的内容编码格式
            metadata.setContentEncoding("utf-8");
            //文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
            //如果没有扩展名则填默认值application/octet-stream
            metadata.setContentType(getContentType(fileName));
            //指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
            metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
            //上传文件   (上传文件流的形式)
            PutObjectResult putResult = ossClient.putObject(aliyunOssConfig.getBucketImgName(), folder + fileName, in, metadata);
            resultStr = putResult.getETag();
            log.info("上传阿里云OSS服务器成功." + resultStr);
            //解析结果
        } catch (Exception e) {
            e.printStackTrace();
            log.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
        }
        return folder + fileName;
    }


}
