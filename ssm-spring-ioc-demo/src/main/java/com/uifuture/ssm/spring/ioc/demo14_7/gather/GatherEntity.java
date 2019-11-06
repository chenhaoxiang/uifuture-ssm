/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.spring.ioc.demo14_7.gather;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

/**
 * 演示注入简单值和集合
 *
 * @author chenhx
 * @version GatherEntity.java, v 0.1 2019-02-12 22:22 chenhx
 */
public class GatherEntity {
    private String name;
    private List<String> citys;
    private Set<String> friends;
    private Map<Integer, String> books;

    /**
     * Setter method for property <tt>name</tt>.
     *
     * @param name value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter method for property <tt>citys</tt>.
     *
     * @param citys value to be assigned to property citys
     */
    public void setCitys(List<String> citys) {
        this.citys = citys;
    }

    /**
     * Setter method for property <tt>friends</tt>.
     *
     * @param friends value to be assigned to property friends
     */
    public void setFriends(Set<String> friends) {
        this.friends = friends;
    }

    /**
     * Setter method for property <tt>books</tt>.
     *
     * @param books value to be assigned to property books
     */
    public void setBooks(Map<Integer, String> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GatherEntity.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("citys=" + citys)
                .add("friends=" + friends)
                .add("books=" + books)
                .toString();
    }
}