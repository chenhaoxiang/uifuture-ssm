/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.util.word;

/**
 * 全角/半角转换
 *
 * @author chenhx
 * @version BCConvert.java, v 0.1 2019-09-23 01:32 chenhx
 */
public class BCConvert {

    /**
     * ASCII表中可见字符从!开始，偏移位值为33(Decimal)
     * // 半角!
     */
    private static final char DBC_CHAR_START = 33;

    /**
     * ASCII表中可见字符到~结束，偏移位值为126(Decimal)
     * // 半角~
     */
    private static final char DBC_CHAR_END = 126;

    /**
     * 全角对应于ASCII表的可见字符从！开始，偏移值为65281
     * // 全角！
     */
    private static final char SBC_CHAR_START = 65281;

    /**
     * 全角对应于ASCII表的可见字符到～结束，偏移值为65374
     * // 全角～
     */
    private static final char SBC_CHAR_END = 65374;

    /**
     * ASCII表中除空格外的可见字符与对应的全角字符的相对偏移
     * // 全角半角转换间隔
     */
    private static final int CONVERT_STEP = 65248;

    /**
     * 全角空格的值，它没有遵从与ASCII的相对偏移，必须单独处理
     * // 全角空格 12288
     */
    private static final char SBC_SPACE = 12288;

    /**
     * 半角空格的值，在ASCII中为32(Decimal)
     * // 半角空格
     */
    private static final char DBC_SPACE = ' ';

    /**
     * <PRE>
     * 半角字符->全角字符转换
     * 只处理空格，!到˜之间的字符，忽略其他
     * </PRE>
     */
    public static String bj2qj(String src) {
        if (src == null) {
            return src;
        }
        StringBuilder buf = new StringBuilder(src.length());
        char[] ca = src.toCharArray();
        for (int i = 0; i < ca.length; i++) {
            // 如果是半角空格，直接用全角空格替代
            if (ca[i] == DBC_SPACE) {
                buf.append(SBC_SPACE);
            } else if ((ca[i] >= DBC_CHAR_START) && (ca[i] <= DBC_CHAR_END)) {
                // 字符是!到~之间的可见字符
                buf.append((char) (ca[i] + CONVERT_STEP));
            } else {
                // 不对空格以及ascii表中其他可见字符之外的字符做任何处理
                buf.append(ca[i]);
            }
        }
        return buf.toString();
    }

    /**
     * 半角转换全角
     *
     * @param src
     * @return
     */
    public static int bj2qj(char src) {
        int r = src;
        if (src == DBC_SPACE) {
            // 如果是半角空格，直接用全角空格替代
            src = SBC_SPACE;
        } else if ((src >= DBC_CHAR_START) && (src <= DBC_CHAR_END)) {
            // 字符是!到~之间的可见字符
            r = src + CONVERT_STEP;
        }
        return r;
    }

    /**
     * <PRE>
     * 全角字符->半角字符转换
     * 只处理全角的空格，全角！到全角～之间的字符，忽略其他
     * </PRE>
     */
    public static String qj2bj(String src) {
        if (src == null) {
            return src;
        }
        StringBuilder buf = new StringBuilder(src.length());
        char[] ca = src.toCharArray();
        for (int i = 0; i < src.length(); i++) {
            if (ca[i] >= SBC_CHAR_START && ca[i] <= SBC_CHAR_END) {
                // 如果位于全角！到全角～区间内
                buf.append((char) (ca[i] - CONVERT_STEP));
            } else if (ca[i] == SBC_SPACE) {
                // 如果是全角空格
                buf.append(DBC_SPACE);
            } else {
                // 不处理全角空格，全角！到全角～区间外的字符
                buf.append(ca[i]);
            }
        }
        return buf.toString();
    }

    /**
     * 全角转换半角
     *
     * @param src
     * @return
     */
    public static int qj2bj(char src) {
        int r = src;
        if (src >= SBC_CHAR_START && src <= SBC_CHAR_END) {
            // 如果位于全角！到全角～区间内
            r = src - CONVERT_STEP;
        } else if (src == SBC_SPACE) {
            // 如果是全角空格
            r = DBC_SPACE;
        }
        return r;
    }

}
