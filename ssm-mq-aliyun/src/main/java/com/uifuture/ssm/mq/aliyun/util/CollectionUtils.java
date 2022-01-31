/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.mq.aliyun.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenhx
 * @version CollectionUtils.java, v 0.1 2019-11-14 16:35 chenhx
 */
public class CollectionUtils {
    /**
     * map是否为空
     */
    public static boolean isEmpty(Map<? extends Object, ? extends Object> map) {
        return map == null || map.isEmpty();
    }

    /**
     * map是否不为空
     */
    public static boolean isNotEmpty(Map<? extends Object, ? extends Object> map) {
        return map != null && !map.isEmpty();
    }

    /**
     * 将Map转换为List
     */
    public static List<? extends Object> parseMap2List(Map<? extends Object, ? extends Object> map) {
        List<Object> list = null;
        if (isNotEmpty(map)) {
            list = new ArrayList<Object>();
            for (Map.Entry<? extends Object, ? extends Object> entry : map.entrySet()) {
                list.add(entry.getValue());
            }
        }
        return list;
    }

    public static <K, V> Map<K, Object> convertMapValueToObject(Map<K, V> map) {
        Map<K, Object> retMap = new HashMap<>();
        if (CollectionUtils.isEmpty(map)) {
            return retMap;
        }
        for (Map.Entry<K, V> entry : map.entrySet()) {
            retMap.put(entry.getKey(), entry.getValue());
        }
        return retMap;
    }

    public static boolean isEmpty(Collection<? extends Object> list) {
        if (list == null || list.isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(Collection<? extends Object> list) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        return true;
    }


    /**
     * 把list转换成string，中间以combineChar来连接
     *
     * @param lists
     * @param combineChar
     * @return
     */
    public static <T> String combineListToString(List<T> lists, char combineChar) {
        StringBuffer sb = new StringBuffer();
        int i = 0;
        for (T l : lists) {
            if (i > 0) {
                sb.append(combineChar);
            }
            i++;
            sb.append(String.valueOf(l));
        }

        return sb.toString();
    }

    /**
     * 把list转换成string，中间以combineChar来连接
     *
     * @param lists
     * @param combineStr
     * @return
     */
    public static <T> String combineListToString(List<T> lists, String combineStr) {
        StringBuffer sb = new StringBuffer();
        int i = 0;
        for (T l : lists) {
            if (i > 0) {
                sb.append(combineStr);
            }
            i++;
            sb.append(String.valueOf(l));
        }

        return sb.toString();
    }
}
