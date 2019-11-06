/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * @author chenhx
 * @version XssUtil.java, v 0.1 2019-09-23 02:25 chenhx
 */
public class XssUtils {

    private static Pattern scriptPattern = Pattern.compile("<[\r\n| | ]*script[\r\n| | ]*>(.*?)</[\r\n| | ]*script[\r\n| | ]*>", Pattern.CASE_INSENSITIVE);
    private static Pattern scriptPattern2 = Pattern.compile("src[\r\n| | ]*=[\r\n| | ]*[\\\"|\\\'](.*?)[\\\"|\\\']", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
    private static Pattern scriptPattern3 = Pattern.compile("</[\r\n| | ]*script[\r\n| | ]*>", Pattern.CASE_INSENSITIVE);
    private static Pattern scriptPattern4 = Pattern.compile("<[\r\n| | ]*script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
    private static Pattern scriptPattern5 = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
    private static Pattern scriptPattern6 = Pattern.compile("e-xpression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
    private static Pattern scriptPattern7 = Pattern.compile("javascript[\r\n| | ]*:[\r\n| | ]*", Pattern.CASE_INSENSITIVE);
    private static Pattern scriptPattern8 = Pattern.compile("vbscript[\r\n| | ]*:[\r\n| | ]*", Pattern.CASE_INSENSITIVE);
    private static Pattern scriptPattern9 = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

    /**
     * 特殊符号处理
     *
     * @param maxLength
     * @return
     */
    public static String getXssFilter(String summary, int maxLength) {
        return getXssFilter(summary, maxLength, "");
    }

    public static String getXssFilter(String text, int maxLength, String replacement) {
        if (StringUtils.isEmpty(text)) {
            return "";
        }
        // 处理
        text = text.replace("<", "&lt;");
        text = text.replace(">", "&gt;");
        text = text.replace("\"", "'");
        text = text.replace("\n", replacement);
        text = xssHand(text);
        if (text.length() > maxLength) {
            text = text.substring(0, maxLength - 1);
        }
        return text;
    }

    /**
     * 将容易引起xss & sql漏洞的半角字符直接替换成全角字符
     *
     * @param s
     * @return
     */
    public static String xssEncode(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        } else {
            s = stripXSSAndSql(s);
        }
        return xssHand(s);
    }

    /**
     * 半角转换为全角
     *
     * @param s
     * @return
     */
    public static String xssHand(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '>':
                    // 转义大于号
                    sb.append("＞");
                    break;
                case '<':
                    // 转义小于号
                    sb.append("＜");
                    break;
                case '&':
                    // 转义&
                    sb.append("＆");
                    break;
                case '#':
                    // 转义#
                    sb.append("＃");
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        return sb.toString();
    }

    /**
     * 防止xss跨脚本攻击（替换，根据实际情况调整）
     */

    private static String stripXSSAndSql(String value) {
        if (value != null) {
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid anything in a src="http://www.yihaomen.com/article/java/..." type of e-xpression
            value = scriptPattern2.matcher(value).replaceAll("");
            // Remove any lonesome </script> tag
            value = scriptPattern3.matcher(value).replaceAll("");
            // Remove any lonesome <script ...> tag
            value = scriptPattern4.matcher(value).replaceAll("");
            // Avoid eval(...) expressions
            value = scriptPattern5.matcher(value).replaceAll("");
            // Avoid e-xpression(...) expressions
            value = scriptPattern6.matcher(value).replaceAll("");
            // Avoid javascript:... expressions
            value = scriptPattern7.matcher(value).replaceAll("");
            // Avoid vbscript:... expressions
            value = scriptPattern8.matcher(value).replaceAll("");
            // Avoid onload= expressions
            value = scriptPattern9.matcher(value).replaceAll("");
        }
        return value;
    }

}
