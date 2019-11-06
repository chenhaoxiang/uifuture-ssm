/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.ssm.spring.ioc.entity;

/**
 * 对象目标
 *
 * @author chenhx
 * @version ObjectTarget.java, v 0.1 2018-12-26 下午 8:08 chenhx
 */
public class ObjectTarget {
    private Integer age;
    private Boolean gender;
    private String name;

    /**
     * Getter method for property <tt>age</tt>.
     *
     * @return property value of age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Setter method for property <tt>age</tt>.
     *
     * @param age value to be assigned to property age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * Getter method for property <tt>gender</tt>.
     *
     * @return property value of gender
     */
    public Boolean getGender() {
        return gender;
    }

    /**
     * Setter method for property <tt>gender</tt>.
     *
     * @param gender value to be assigned to property gender
     */
    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    /**
     * Getter method for property <tt>name</tt>.
     *
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property <tt>name</tt>.
     *
     * @param name value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ObjectTarget{");
        sb.append(super.toString());
        sb.append(",");
        sb.append("age=").append(age);
        sb.append(", gender=").append(gender);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}