/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.classdemo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 没有使用泛型的BeanUtils
 *
 * @author chenhx
 * @version BeanUtilsNoGenerics.java, v 0.1 2018-07-23 下午 9:16
 */
public class BeanUtilsNoGenerics {
    public static Object populate(Class cls, Map map) throws ReflectiveOperationException {
        //1 用类反射new出对象
        Object obj = cls.newInstance();
        //2 再用类反射对新new的对象设置属性值(必须遵守Java设置规范)--即通过setter方法设置
        //2.1遍历出所有该类声明的属性
        Field[] fields = cls.getDeclaredFields();
        for (Field fld : fields) {
            //获取该fld对象所代表的属性名
            String fldName = fld.getName();
            //根据属性名，到map中去读取数据，只有数据非空才需要给该属性设置值
            Object mapValue = map.get(fldName);
            if (mapValue == null) {
                //如果map中不存在对应的属性数据，我们在这里给出提示信息  实际中请删除
                System.out.println(fldName + "的数据为空！");
            } else {
                //如果map中存在对应的属性数据，则由属性名得出它的setter方法的名字，注意使用命名规则
                String setName = "set" + fldName.substring(0, 1).toUpperCase() + fldName.substring(1);

                //根据方法名和参数的数据类型(其实就是属性的类型)，获得Method对象
                Class[] parameterTypes = new Class[1];
                parameterTypes[0] = fld.getType();
                //最好不要用getMethod，因为这样可能会用到父类的方法
                Method method = cls.getDeclaredMethod(setName, parameterTypes);
                //这里不用担心会访问到私有的，因为权限没放开。
                //调用该method对象所代表的方法
                Object[] args = new Object[1];
                args[0] = mapValue;
                method.invoke(obj, args);
            }
        }
        return obj;
    }

    /**
     * 测试类
     *
     * @param args
     */
    public static void main(String[] args) throws ReflectiveOperationException {
        Map<String, Object> map = new HashMap<>(16);
        map.put("name", "test");
        map.put("age", 21);
        map.put("address", "浙江杭州");
        map.put("createTime", new Date());
        Users users = (Users) populate(Users.class, map);
        System.out.println(users);
    }
}