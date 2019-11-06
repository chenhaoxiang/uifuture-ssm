package com.uifuture.ssm.util;


import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 密码加密工具
 */
public class PasswordUtils {

    /**
     * 用于随机选的数字
     */
    private static final String BASE_NUMBER = "0123456789";
    /**
     * 用于随机选的字符
     */
    private static final String BASE_CHAR = "abcdefghijklmnopqrstuvwxyz";
    /**
     * 用于随机选的字符和数字
     */
    private static final String BASE_CHAR_NUMBER = BASE_CHAR + BASE_NUMBER;

    /**
     * @return 返回加密用的盐
     */
    public static String getToken() {
        return DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS") + randomStringLower(15);
    }


    /**
     * 获得一个随机的字符串（只包含数字和大写字符）
     *
     * @param length 字符串的长度
     * @return 随机字符串
     * @since 4.0.13
     */
    public static String randomStringLower(int length) {
        return randomString(BASE_CHAR_NUMBER, length).toLowerCase();
    }

    /**
     * 获得一个随机的字符串（只包含数字）
     *
     * @param length 字符串的长度
     * @return 随机字符串
     * @since 4.0.13
     */
    public static String randomNumberLower(int length) {
        return randomString(BASE_NUMBER, length);
    }

    /**
     * 获得一个随机的字符串
     *
     * @param baseString 随机字符选取的样本
     * @param length     字符串的长度
     * @return 随机字符串
     */
    public static String randomString(String baseString, int length) {
        final StringBuilder sb = new StringBuilder();

        if (length < 1) {
            length = 1;
        }
        int baseLength = baseString.length();
        for (int i = 0; i < length; i++) {
            int number = getRandom().nextInt(baseLength);
            sb.append(baseString.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 获取随机数生成器对象<br>
     * ThreadLocalRandom是JDK 7之后提供并发产生随机数，能够解决多个线程发生的竞争争夺。
     *
     * @return {@link ThreadLocalRandom}
     * @since 3.1.2
     */
    public static ThreadLocalRandom getRandom() {
        return ThreadLocalRandom.current();
    }


    /**
     * @return 返回加密用的盐
     */
    public static String getSalt() {
        return java.util.UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 用户登录等密码的加密方法
     *
     * @param password
     * @param salt
     * @return
     */
    public static String getPassword(String password, String salt) {
        return MD5Utils.md5Encode(MD5Utils.md5Encode(password) + salt);
    }

    /**
     * 生成 token 的方式-验证的令牌，不会过期的token
     * 在用户点击验证邮箱地址时使用，密码使用的是盐和加密后的密码！
     *
     * @param salt
     * @param password
     * @return
     */
    public static String getTokenForever(String salt, String password) {
        return getPassword(MD5Utils.md5Encode(salt + password), salt);
    }


    /**
     * 生成 token 的方式-验证的令牌
     * 在用户点击验证邮箱地址时使用，密码使用的是盐和加密后的密码！
     *
     * @param salt
     * @param password
     * @return
     */
    public static String getToken(String salt, String password, Long time) {
        return getPassword(MD5Utils.md5Encode(salt + password), time.toString());
    }

    public static String getToken(String salt, Long time) {
        return getPassword(MD5Utils.md5Encode(salt), time.toString());
    }

}
