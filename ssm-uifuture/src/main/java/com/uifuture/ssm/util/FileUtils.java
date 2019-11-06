/**
 * copyfuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.ssm.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 定制的文件工具类
 *
 * @author chenhx
 * @version FileUtils.java, v 0.1 2018-09-03 下午 7:24
 */
public class FileUtils {

    public static void main(String[] args) {
        System.out.println(getFilesName("/Users/chenhx/Desktop/imagesLocal/", 5));
    }

    /**
     * 递归取到当前目录所有文件
     *
     * @param dir
     * @return
     */
    public static List<String> getFilesName(String dir) {
        List<String> lstFiles = new ArrayList<>();
        File[] files = new File(dir).listFiles();
        if (files == null) {
            return lstFiles;
        }
        for (File f : files) {
            if (f.isDirectory()) {
                lstFiles.addAll(getFilesName(f.getAbsolutePath()));
            } else {
                lstFiles.add(f.getAbsolutePath());
            }
        }
        return lstFiles;
    }


    /**
     * 递归取到当前目录指定数量的文件
     *
     * @param dir
     * @return
     */
    public static List<String> getFilesName(String dir, Integer size) {
        List<String> lstFiles = new ArrayList<>();
        File[] files = new File(dir).listFiles();
        if (files == null) {
            return lstFiles;
        }
        for (File f : files) {
            if (f.isDirectory()) {
                lstFiles.addAll(getFilesName(f.getAbsolutePath(), size));
            } else {
                lstFiles.add(f.getAbsolutePath());
            }
            if (lstFiles.size() > size) {
                return lstFiles;
            }
        }
        return lstFiles;
    }

    /**
     * 创建目录
     *
     * @param destFilePath
     * @return
     */
    public static boolean createDir(String destFilePath) {
        File file = new File(destFilePath);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 移除路径下文件
     *
     * @param path
     */
    public static void removeFiles(String path) {
        File file = new File(path);
        if (file.exists()) {
            deleteFile(file);
        }
    }

    /**
     * 文件是否存在
     *
     * @param path
     */
    public static Boolean exists(String path) {
        File file = new File(path);
        return file.exists();
    }

    /**
     * 删除文件
     *
     * @param file
     */
    public static Boolean deleteFile(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File file1 : files) {
                    deleteFile(file1);
                }
            }
        }
        return file.delete();
    }


    /**
     * 将InputStream写入本地文件
     *
     * @param path     写入本地目录
     * @param fileName 写入本地目录
     * @param input    输入流
     * @throws IOException
     */
    public static void writeToLocal(String path, String fileName, InputStream input)
            throws IOException {
        createDir(path);
        int index;
        byte[] bytes = new byte[1024];
        FileOutputStream downloadFile = new FileOutputStream(path + fileName);
        while ((index = input.read(bytes)) != -1) {
            downloadFile.write(bytes, 0, index);
            downloadFile.flush();
        }
        downloadFile.close();
        input.close();
    }
}
