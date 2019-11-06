/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.ssm.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chenhx
 * @version UnicodeUtil.java, v 0.1 2018-07-01 上午 11:37
 */
public class UnicodeUtil {

    public static final Pattern pattern = Pattern.compile("((\\\\)+u(\\p{XDigit}{4}))");

    /**
     * 汉字字符串转 Unicode
     */
    public static String stringToUnicode(String s) {
        try {
            StringBuffer out = new StringBuffer("");
            //直接获取字符串的unicode二进制
            byte[] bytes = s.getBytes("unicode");
            //然后将其byte转换成对应的16进制表示即可
            for (int i = 0; i < bytes.length - 1; i += 2) {
                out.append("\\u");
                String str = Integer.toHexString(bytes[i + 1] & 0xff);
                for (int j = str.length(); j < 2; j++) {
                    out.append("0");
                }
                String str1 = Integer.toHexString(bytes[i] & 0xff);
                out.append(str1);
                out.append(str);
            }
            return out.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Unicode转 汉字字符串
     */
    public static String unicodeToString(String str) {
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            String group = matcher.group(3);
            ch = (char) Integer.parseInt(group, 16);
            String group1 = matcher.group(1);
            /**
             * 这里防止了两个斜杠出现的unicode，例如\ u****的编码出现。
             */
            str = str.replace("\\" + group1, ch + "").replace(group1, ch + "");
        }
        return str;
    }

}