/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.basics.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件工具类
 *
 * @author chenhx
 * @version FileUtils.java, v 0.1 2018-08-19 下午 4:51
 */
public class FileUtils {
    public static void main(String[] args) {
        List<String> stringList = getFilesNames("D:\\github\\uifuture-ssm\\ssm-spring-mvc\\src\\main\\resources\\static");
        for (String str : stringList) {
            System.out.println(str);
        }
    }

    /**
     * 保存字节数组到文件文件
     *
     * @param filePath
     * @param data
     */
    public static void saveFile(String filePath, byte[] data) throws IOException {
        if (data != null) {
            File file = new File(filePath);
            if (file.exists()) {
                //原文件存在，就先删除原文件
                file.delete();
            }
            FileOutputStream fos = null;
            fos = new FileOutputStream(file);
            fos.write(data, 0, data.length);
            fos.flush();
            fos.close();
        }
    }

    /**
     * 从文件名中获取文件的字节数组
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public static byte[] readFileName(String filename) throws IOException {
        return readInputStream(new FileInputStream(filename));
    }

    /**
     * 从输入流中获取字节数组
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    /**
     * 递归取到当前目录所有文件
     *
     * @param dir
     * @return 返回的是文件的绝对路径
     */
    public static List<String> getFilesNames(String dir) {
        List<String> lstFiles = new ArrayList<>();
        File[] files = new File(dir).listFiles();
        if (files == null || files.length == 0) {
            return lstFiles;
        }
        for (File f : files) {
            //如果是目录，则继续递归
            if (f.isDirectory()) {
                lstFiles.addAll(getFilesNames(f.getAbsolutePath()));
            } else {
                //否则添加进list
                lstFiles.add(f.getAbsolutePath());
            }
        }
        return lstFiles;
    }

}