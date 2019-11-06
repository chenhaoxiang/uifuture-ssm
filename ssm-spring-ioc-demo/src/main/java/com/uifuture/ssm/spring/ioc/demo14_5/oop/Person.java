package com.uifuture.ssm.spring.ioc.demo14_5.oop;

import com.uifuture.ssm.spring.ioc.demo14_5.Say;

import java.util.Calendar;

/**
 * 传统实现(非IOC方式)
 * chenhx
 */
public class Person implements Say {
    /**
     * 需要的引用
     */
    private Calendar cal;

    public Person() {
//         主动获取
        cal = Calendar.getInstance();
    }

    public static void main(String args[]) {
        Person person = new Person();
        System.out.println(person.sayHello());
    }

    @Override
    public String sayHello() {
        if (cal.get(Calendar.AM_PM) == Calendar.AM) {
            return "上午好";
        } else {
            return "下午好";
        }
    }
}
