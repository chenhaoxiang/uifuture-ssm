/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.util;

import java.util.Iterator;

/**
 * @author chenhx
 * @version IteratorsUtils.java, v 0.1 2019-09-14 12:18 chenhx
 */
public class IteratorsUtils {


    public static <T> T getFirst(Iterable<? extends T> iterable, T defaultValue) {
        return getNext(iterable.iterator(), defaultValue);
    }

    public static <T> T getNext(Iterator<? extends T> iterator, T defaultValue) {
        return iterator.hasNext() ? iterator.next() : defaultValue;
    }

}
