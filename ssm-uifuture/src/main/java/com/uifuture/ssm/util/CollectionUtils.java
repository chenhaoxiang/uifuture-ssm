/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author chenhx
 * @version CollectionUtils.java, v 0.1 2019-09-18 15:50 chenhx
 */
public class CollectionUtils {

    /**
     * list集合转换为set
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> Set<T> listToSet(List<T> list) {
        Set<T> set = new HashSet<>();
        set.addAll(list);
        return set;
    }
}
