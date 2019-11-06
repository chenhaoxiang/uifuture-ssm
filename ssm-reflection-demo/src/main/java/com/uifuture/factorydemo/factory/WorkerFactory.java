/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.factorydemo.factory;

import com.uifuture.factorydemo.api.Worker;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Worker工厂
 *
 * @author chenhx
 * @version WorkerFactory.java, v 0.1 2018-07-19 下午 10:52
 */
public class WorkerFactory {
    private static Worker worker = null;

    /**
     * 通过工厂方法获取Worker对象
     *
     * @return
     */
    public static Worker getWorker() {
        if (worker != null) {
            return worker;
        }
        //配置文件
        Properties p = new Properties();
        FileInputStream in;
        try {
            //读取配置文件
            in = new FileInputStream(WorkerFactory.class.getResource("/").getPath()
                    + "worker.properties");
            p.load(in);
            //通过name属性名获取属性值。name 为自定义的
            String className = p.getProperty("name");
            //通过name获得name后面=号后面的字符串，这样就可以通过修改配置文件来new不同的类
            Class c = Class.forName(className);
            //通过反射获取对象的实例化
            worker = (Worker) c.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return worker;
    }

}
