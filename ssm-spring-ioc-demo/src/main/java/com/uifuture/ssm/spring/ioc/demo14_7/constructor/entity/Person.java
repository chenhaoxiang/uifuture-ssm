package com.uifuture.ssm.spring.ioc.demo14_7.constructor.entity;

/**
 * 演示构造函数注入
 */
public class Person {
    private String address;
    private String name;

    /**
     * 构造函数
     */
    public Person(String name, String address) {
        this.address = address;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "address='" + address + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

