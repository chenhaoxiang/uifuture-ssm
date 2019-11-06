/**
 * copyfuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.util;

/**
 * @author chenhx
 * @version FileTypeUtils.java, v 0.1 2019-03-16 01:31 chenhx
 */
public class FileTypeUtils {
    private static final String TYPE_JPG = ".jpg";
    private static final String TYPE_GIF = ".gif";
    private static final String TYPE_PNG = ".png";
    private static final String TYPE_BMP = ".bmp";
    private static final String TYPE_TIF = ".tif";
    private static final String TYPE_UNKNOWN = "unknown";

    /**
     * byte数组转换成16进制字符串
     *
     * @param src
     * @return
     */
    private static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }


    /**
     * 根据文件流判断图片类型
     *
     * @param fis
     * @return jpg/png/gif/bmp
     */
    public static String getPicType(byte[] fis) {
        String type = bytesToHexString(fis).toUpperCase();
        if (type.contains("FFD8FF")) {
            return TYPE_JPG;
        } else if (type.contains("89504E47")) {
            return TYPE_PNG;
        } else if (type.contains("47494638")) {
            return TYPE_GIF;
        } else if (type.contains("424D")) {
            return TYPE_BMP;
        } else if (type.contains("49492A00")) {
            return TYPE_TIF;
        } else {
            return TYPE_UNKNOWN;
        }
    }

}
