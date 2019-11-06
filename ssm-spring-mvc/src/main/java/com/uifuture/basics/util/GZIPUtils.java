/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.basics.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * GZIP压缩工具类
 *
 * @author chenhx
 * @version GZIPUtils.java, v 0.1 2018-08-19 下午 4:46
 */
public class GZIPUtils {
    /**
     * GZIP压缩文件后缀
     */
    private static final String GZIP_FILE_SUFFIX = ".gz";
    /**
     * 静态文件在项目下的路径
     */
    private static final String STATIC_PATH = "/src/main/resources/static/";

    /**
     * 运行该方法即可将静态资源遍历压缩，并且过滤不想进行压缩的文件
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String contestPath = new File("").getCanonicalPath();
        contestPath = contestPath + STATIC_PATH;
        System.out.println(contestPath);
        gzipPath(contestPath);
    }

    /**
     * 排除 GZIP_FILE_SUFFIX 后缀的文件压缩
     *
     * @param path
     * @throws IOException
     */
    public static void gzipPath(String path) throws IOException {
        Set<String> stringSet = new HashSet<>();
        stringSet.add(GZIP_FILE_SUFFIX);
        gzipPath(path, stringSet);
    }

    /**
     * 递归压缩目录下的所有文件，并且在文件同目录下创建压缩文件
     *
     * @param path
     * @param excludeSuffix 过滤文件后缀名包含在Set中的文件
     * @throws IOException
     */
    public static void gzipPath(String path, Set<String> excludeSuffix) throws IOException {
        if (path == null || path.trim().length() == 0) {
            return;
        }
        //遍历出所有的文件
        List<String> stringList = FileUtils.getFilesNames(path);
        for (String filename : stringList) {
            //如果文件的后缀名为需要排除的后缀，则跳过
            String suffix = filename.substring(filename.lastIndexOf("."), filename.length());
            if (excludeSuffix.contains(suffix)) {
                continue;
            }
            //进行获取文件的字节数组
            byte[] fileBytes = FileUtils.readFileName(filename);
            //进行压缩
            byte[] gzipBytes = gzip(fileBytes);
            //存储文件
            FileUtils.saveFile(filename + GZIP_FILE_SUFFIX, gzipBytes);
        }
    }

    /**
     * 将字节数组进行GZIP压缩
     *
     * @param fileBytes
     * @return
     */
    public static byte[] gzip(byte[] fileBytes) throws IOException {
        if (fileBytes == null || fileBytes.length == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip;
        gzip = new GZIPOutputStream(out);
        gzip.write(fileBytes);
        gzip.close();
        return out.toByteArray();
    }

    /**
     * 解压
     *
     * @param bytes
     * @return
     */
    public static byte[] ungzip(byte[] bytes) throws IOException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        GZIPInputStream ungzip = new GZIPInputStream(in);
        byte[] buffer = new byte[512];
        int n;
        while ((n = ungzip.read(buffer)) >= 0) {
            out.write(buffer, 0, n);
        }
        return out.toByteArray();
    }

}