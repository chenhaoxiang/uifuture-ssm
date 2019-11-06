/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.config;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 自定义类加载器
 * 遵循双亲委派模型
 *
 * @author chenhx
 * @version CustomClassLoader.java, v 0.1 2018-07-18 下午 10:22
 */
public class CustomClassLoader extends ClassLoader {

    private String classPath;

    public CustomClassLoader(String classPath) {
        this.classPath = classPath;
    }

    public CustomClassLoader(ClassLoader parent, String classPath) {
        super(parent);
        this.classPath = classPath;
    }

    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //注意，这里的是class文件的路径
        CustomClassLoader customClassLoader = new CustomClassLoader("/Users/chenhx/Desktop/github/uifuture-ssm/ssm-classLoader/target/classes/");
        Class loadClass = customClassLoader.findClass("com.uifuture.entity.User");
        Object object = loadClass.newInstance();
        System.out.println(object);
        //打印出自定义的类加载器
        System.out.println(object.getClass().getClassLoader());
    }

    /**
     * 重写方法
     *
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        //加载类的二进制流
        byte[] classData = getClassBytes(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(name, classData, 0, classData.length);
        }
    }

    /**
     * 读取文件的二进制流
     *
     * @param className class类的全限定名
     * @return
     */
    private byte[] getClassBytes(String className) {
        //拼接路径
        String path = classPath + className.replace('.', '\\') + ".class";
        try {
            //使用字节流获取字节码文件，因为字节码文件是二进制流
            InputStream is = new FileInputStream(path);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            byte[] buffer = new byte[2048];
            int num;
            while ((num = is.read(buffer)) != -1) {
                stream.write(buffer, 0, num);
            }
            return stream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
