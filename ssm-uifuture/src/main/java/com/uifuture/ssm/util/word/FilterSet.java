/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.util.word;

/**
 * @author chenhx
 * @version FilterSet.java, v 0.1 2019-09-23 01:31 chenhx
 */
public class FilterSet {

    private final long[] elements;


    public FilterSet() {
        elements = new long[1 + (65535 >>> 6)];
    }

    public static void main(String[] args) {
        FilterSet oi = new FilterSet();
        System.out.println(oi.elements.length);
    }

    public void add(final int no) {
        elements[no >>> 6] |= (1L << (no & 63));
    }

    public void add(final int... no) {
        for (int currNo : no) {
            elements[currNo >>> 6] |= (1L << (currNo & 63));
        }
    }

    public void remove(final int no) {
        elements[no >>> 6] &= ~(1L << (no & 63));
    }

    /**
     * @param no
     * @return true:添加成功	false:原已包含
     */
    public boolean addAndNotify(final int no) {
        int eWordNum = no >>> 6;
        long oldElements = elements[eWordNum];
        elements[eWordNum] |= (1L << (no & 63));
        return elements[eWordNum] != oldElements;
    }

    /**
     * @param no
     * @return true:移除成功	false:原本就不包含
     */
    public boolean removeAndNotify(final int no) {
        int eWordNum = no >>> 6;
        long oldElements = elements[eWordNum];
        elements[eWordNum] &= ~(1L << (no & 63));
        return elements[eWordNum] != oldElements;
    }

    public boolean contains(final int no) {
        return (elements[no >>> 6] & (1L << (no & 63))) != 0;
    }

    public boolean containsAll(final int... no) {
        if (no.length == 0) {
            return true;
        }
        for (int currNo : no) {
            if ((elements[currNo >>> 6] & (1L << (currNo & 63))) == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 目前没有去维护size，每次都是去计算size
     *
     * @return
     */
    public int size() {
        int size = 0;
        for (long element : elements) {
            size += Long.bitCount(element);
        }
        return size;
    }

}
