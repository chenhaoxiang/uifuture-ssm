package com.uifuture.ssm.spring.ioc.demo14_5.ioc;

import com.uifuture.ssm.spring.ioc.demo14_5.Say;

import java.util.Calendar;

/**
 * IOC方式实现
 * chenhx
 */
public class Person implements Say {
    /**
     * 需要的引用
     */
    private Calendar cal;

    public static void main(String args[]) {
        Person person = new Person();
        person.setCal(Calendar.getInstance());
        System.out.println(person.sayHello());
    }

    /**
     * 依赖注入
     *
     * @param cal
     */
    public void setCal(Calendar cal) {
        this.cal = cal;
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
