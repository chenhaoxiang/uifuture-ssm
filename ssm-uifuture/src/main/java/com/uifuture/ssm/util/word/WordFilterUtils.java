/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.util.word;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * 思路： 创建一个FilterSet，枚举了0~65535的所有char是否是某个敏感词开头的状态
 * <p>
 * 判断是否是 敏感词开头 | | 是 不是 获取头节点 OK--下一个字 然后逐级遍历，DFA算法
 * </p>
 *
 * @author chenhx
 * @version WordFilterUtils.java, v 0.1 2019-09-23 01:30 chenhx
 */
public class WordFilterUtils {
    /**
     * 存储首字
     */
    private static final FilterSet FILTER_SET = new FilterSet();
    /**
     * 存储节点
     */
    private static final Map<Integer, WordNode> NODES = new HashMap<>(1024, 1);
    /**
     * 停顿词
     */
    private static final Set<Integer> STOPWD_SET = new HashSet<>();
    /**
     * 敏感词过滤替换
     */
    private static final char SIGN = '*';

    /**
     * 单机测试加载
     */
//    static {
//        try {
    //使用
//			init();
//        } catch (Exception e) {
    // 加载失败
//        }
//    }
    private static void init() {
        // 获取敏感词
        addSensitiveWord(readWordFromFile("wd.txt"));
        addStopWord(readWordFromFile("stopwd.txt"));
    }

    /**
     * 增加敏感词
     * 遍历文件，将敏感词按行读取
     *
     * @param path
     * @return
     */
    public static List<String> readWordFromFile(String path) {
        List<String> words;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(WordFilterUtils.class.getClassLoader().getResourceAsStream(path))))) {
            words = new ArrayList<>(1024);
            for (String buf = ""; (buf = br.readLine()) != null; ) {
                if ("".equals(buf.trim())) {
                    continue;
                }
                words.add(buf);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return words;
    }

    /**
     * 增加停顿词
     * 遍历停顿词，将停顿词添加到STOPWD_SET中
     *
     * @param words
     */
    public static void addStopWord(final List<String> words) {
        if (!isEmpty(words)) {
            char[] chs;
            for (String curr : words) {
                chs = curr.toCharArray();
                for (char c : chs) {
                    STOPWD_SET.add(charConvert(c));
                }
            }
        }
    }

    /**
     * 添加DFA节点
     * 添加敏感字开头的字符，以及增加敏感字节点
     *
     * @param words
     */
    public static void addSensitiveWord(final List<String> words) {
        if (!isEmpty(words)) {
            char[] chs;
            int fchar;
            int lastIndex;
            WordNode fnode; // 首字母节点
            for (String curr : words) {
                chs = curr.toCharArray();
                fchar = charConvert(chs[0]);
                //没有首字定义
                if (!FILTER_SET.contains(fchar)) {
                    // 首字标志位 可重复add,已经判断了，不重复了
                    FILTER_SET.add(fchar);
                    fnode = new WordNode(fchar, chs.length == 1);
                    NODES.put(fchar, fnode);
                } else {
                    fnode = NODES.get(fchar);
                    if (!fnode.isLast() && chs.length == 1) {
                        fnode.setLast(true);
                    }
                }
                lastIndex = chs.length - 1;
                for (int i = 1; i < chs.length; i++) {
                    fnode = fnode.addIfNoExist(charConvert(chs[i]), i == lastIndex);
                }
            }
        }
    }

    /**
     * 过滤判断 将敏感词转化为成屏蔽词
     *
     * @param src
     * @return
     */
    public static String doFilter(final String src) {
        char[] chs = src.toCharArray();
        int length = chs.length;
        // 当前检查的字符
        int currc;
        // 当前检查字符的备份
        int cpcurrc;
        int k;
        WordNode node;
        for (int i = 0; i < length; i++) {
            currc = charConvert(chs[i]);
            if (!FILTER_SET.contains(currc)) {
                continue;
            }
            node = NODES.get(currc);
            if (node == null) {
                continue;
            }
            boolean couldMark = false;
            int markNum = -1;
            if (node.isLast()) {
                // 单字匹配
                couldMark = true;
                markNum = 0;
            }
            // 继续匹配，以长的优先
            k = i;
            // 当前字符的拷贝
            cpcurrc = currc;
            for (; ++k < length; ) {
                int temp = charConvert(chs[k]);
                if (temp == cpcurrc) {
                    continue;
                }
                if (STOPWD_SET.contains(temp)) {
                    continue;
                }
                node = node.querySub(temp);
                if (node == null) {
                    break;
                }
                if (node.isLast()) {
                    couldMark = true;
                    // 3-2
                    markNum = k - i;
                }
                cpcurrc = temp;
            }
            if (couldMark) {
                for (k = 0; k <= markNum; k++) {
                    chs[k + i] = SIGN;
                }
                i = i + markNum;
            }
        }
        return new String(chs);

    }

    /**
     * 是否包含敏感词
     *
     * @param src
     * @return true-包含
     */
    public static boolean isContains(final String src) {
        if (FILTER_SET.size() > 0 && NODES.size() > 0) {
            char[] chs = src.toCharArray();
            int length = chs.length;
            // 当前检查的字符
            int currc;
            // 当前检查字符的备份
            int cpcurrc;
            int k;
            WordNode node;
            for (int i = 0; i < length; i++) {
                currc = charConvert(chs[i]);
                if (!FILTER_SET.contains(currc)) {
                    continue;
                }
                node = NODES.get(currc);
                if (node == null) {
                    // 不会发生
                    continue;
                }
                boolean couldMark = false;
                if (node.isLast()) {
                    // 单字匹配
                    couldMark = true;
                }
                // 继续匹配 ，以长的优先
                k = i;
                cpcurrc = currc;
                for (; ++k < length; ) {
                    int temp = charConvert(chs[k]);
                    if (temp == cpcurrc) {
                        continue;
                    }
                    if (STOPWD_SET.contains(temp)) {
                        continue;
                    }
                    node = node.querySub(temp);
                    if (node == null) {
                        //没有下一个
                        break;
                    }
                    if (node.isLast()) {
                        couldMark = true;
                    }
                    cpcurrc = temp;
                }
                if (couldMark) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 大写转化为小写 全角转化为半角
     *
     * @param src
     * @return
     */
    private static int charConvert(char src) {
        int r = BCConvert.qj2bj(src);
        return (r >= 'A' && r <= 'Z') ? r + 32 : r;
    }

    /**
     * 判断一个集合是否为空
     *
     * @param col
     * @return
     */
    public static <T> boolean isEmpty(final Collection<T> col) {
        return col == null || col.isEmpty();
    }
}
