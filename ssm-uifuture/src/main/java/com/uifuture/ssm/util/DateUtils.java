package com.uifuture.ssm.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2017/3/16.
 * Time: 下午 9:03.
 * Explain: 时间日期转换的工具类
 * 继承org.apache.commons.lang.time.DateUtils类
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss SSS", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss SSS", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss SSS", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM",
            "yyyy年MM月dd日", "yyyy年MM月dd日 HH时mm分ss秒SSS毫秒", "yyyy年MM月dd日 HH时mm分ss秒",
            "yyyy年MM月dd日 HH时mm分", "yyyy年MM月", "EEE MMM dd HH:mm:ss zzz yyyy"
    };


    /**
     * 得到当前日期-到秒
     *
     * @return
     */
    public static Date getDateTime() throws ParseException {
        return parseDate(getDateString("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前日期-到毫秒级别
     *
     * @return
     */
    public static Date getDateTimeMS() throws ParseException {
        return parseDate(getDateString("yyyy-MM-dd HH:mm:ss SSS"), "yyyy-MM-dd HH:mm:ss SSS");
    }

    /**
     * 得到当前日期 默认只有年月日
     *
     * @return
     */
    public static Date getDate() throws ParseException {
        return parseDate(getDateString("yyyy-MM-dd"), "yyyy-MM-dd");
    }

    /**
     * 得到当前日期  格式类似（yyyy-MM-dd）
     *
     * @param pattern
     * @return
     */
    public static Date getDate(String pattern) throws ParseException {
        return parseDate(getDateString(pattern), pattern);
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） 默认只有年月日
     *
     * @return
     */
    public static String getDateString() {
        return getDateString("yyyy-MM-dd");
    }

    /**
     * 得到当前日期 +  day 的日期 字符串 格式（yyyy-MM-dd） 默认只有年月日
     *
     * @param day 负数为当前日期之前的天
     * @return
     */
    public static String getDateString(Integer day) {
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return DateFormatUtils.format(now, "yyyy-MM-dd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     * pattern可以为："yyyy.MM.dd HH:mm:ss SSS" "yyyy-MM-dd" "HH:mm:ss" "E"
     *
     * @param pattern
     * @return
     */
    public static String getDateString(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    public static String getDateString(Date date, String pattern) {
        return DateFormatUtils.format(date, pattern);
    }


    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd）
     * pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E" pattern只有第一个有效
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     *
     * @param date
     * @return
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到日期时间毫秒字符串，转换格式（yyyy-MM-dd HH:mm:ss SSS）
     *
     * @param date
     * @return
     */
    public static String formatDateTimeMS(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss SSS");
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     *
     * @return
     */
    public static String getTimeString() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     *
     * @return
     */
    public static String getDateTimeString() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyyMMddHHmmssSSS）
     *
     * @return
     */
    public static String getDateTimeMSString() {
        return formatDate(new Date(), "yyyyMMddHHmmssSSS");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     *
     * @return
     */
    public static String getYearString() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     *
     * @return
     */
    public static String getMonthString() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当 日的字符串 格式（dd）
     *
     * @return
     */
    public static String getDayString() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     *
     * @return
     */
    public static String getWeekString() {
        return formatDate(new Date(), "E");
    }

    /**
     * 日期型字符串转化为 日期 格式
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 日期型字符串转化为 long型数字
     */
    public static Long string2Long(String str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns).getTime();
        } catch (ParseException e) {
            return null;
        }
    }


    /**
     * 格林威治日期格式的字符串转换为Date
     */
    public static Date parseGreenwichDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), Locale.US, "E MMM dd HH:mm:ss z yyyy");
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * long型时间转换成对应规则的String时间
     *
     * @param dateLong long型时间
     * @param pattern  SimpleDateFormat的解析规则，例如:yyyy-MM-dd HH:mm:ss SSS
     *                 默认为:yyyy-MM-dd HH:mm:ss
     * @return 解析后的时间字符串
     */
    public static String long2String(Long dateLong, String... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(dateLong, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(dateLong, "yyyy-MM-dd HH:mm:ss");
        }
        return formatDate;
    }

    /**
     * 获取两个日期之间的天数
     *
     * @param before
     * @param after
     * @return
     */
    public static double getDistanceOf2Date(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        if (afterTime < beforeTime) {
            afterTime = afterTime ^ beforeTime;
            beforeTime = afterTime ^ beforeTime;
            afterTime = afterTime ^ beforeTime;
        }
        return (afterTime - beforeTime) / (1000.0 * 60.0 * 60.0 * 24.0);
    }

    /**
     * long型时间-转换为时间（天,时:分:秒.毫秒）
     *
     * @param timeMillis
     * @return
     */
    public static String formatDateTime(long timeMillis) {
        long day = timeMillis / (24 * 60 * 60 * 1000);
        long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
        long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
        return (day > 0 ? day + "天" : "") + hour + "小时" + min + "分钟" + s + "秒" + sss + "毫秒";
    }

    /**
     * long型时间-转换为时间（天,时:分:秒）
     *
     * @param timeMillis
     * @return
     */
    public static String formatDateTimeNoSss(long timeMillis) {
        long day = timeMillis / (24 * 60 * 60 * 1000);
        long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
        long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        return (day > 0 ? day + "天" : "") + hour + "小时" + min + "分钟" + s + "秒";
    }

    /**
     * long型时间-转换为时间（天,03时:01分:02秒）
     *
     * @param timeMillis
     * @return
     */
    public static String formatDateTimeNoSssAndZero(long timeMillis) {
        if (timeMillis < 0) {
            timeMillis = 0;
        }
        long day = timeMillis / (24 * 60 * 60 * 1000);
        long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
        long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        return (day > 0 ? day + "天" : "") + (hour > 9 ? hour : "0" + hour) + "小时" + (min > 9 ? min : "0" + min) + "分钟" + (s > 9 ? s : "0" + s) + "秒";
    }


    /**
     * 获取现在到过去某个时间间隔的天数
     *
     * @param date -传入某个时间
     * @return
     */
    public static long pastDays(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取现在的毫秒级别的long型值
     *
     * @return
     */
    public static long getLongDateTimeMS() {
        return System.currentTimeMillis();
    }

    /**
     * 获取 秒 的时间戳
     *
     * @return
     */
    public static int getIntDateTimeS() {
        return (int) System.currentTimeMillis() / 1000;
    }

    /**
     * 获取现在到过去某个时间间隔的小时
     *
     * @param date -传入某个时间
     * @return
     */
    public static long pastHour(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (60 * 60 * 1000);
    }

    /**
     * 获取现在到过去某个时间间隔的分钟
     *
     * @param date -传入某个时间
     * @return
     */
    public static long pastMinutes(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (60 * 1000);
    }

    /**
     * 获取现在到过去某个时间间隔的秒数
     *
     * @param date -传入某个时间
     * @return
     */
    public static long pastSecond(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (1000);
    }


}
