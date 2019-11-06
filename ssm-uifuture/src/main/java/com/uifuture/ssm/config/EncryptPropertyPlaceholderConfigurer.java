/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.ssm.config;

import com.uifuture.ssm.util.AES;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.TypedStringValue;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurablePropertyResolver;
import org.springframework.util.StringValueResolver;


/**
 * 加密解密账号密码
 *
 * @author chenhx
 * @version EncryptPropertyPlaceholderConfigurer.java, v 0.1 2018-07-01 下午 12:47
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertySourcesPlaceholderConfigurer {
    /**
     * 注意！这里的值是真正需要注入的Bean的属性名，而不是我们配置文件中的属性名
     */
    private String[] encryptPropNames = {"username", "password"};

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, ConfigurablePropertyResolver propertyResolver) throws BeansException {
        super.processProperties(beanFactoryToProcess, propertyResolver);
        BeanDefinition bd = beanFactoryToProcess.getBeanDefinition("dataSource");
        MutablePropertyValues pv = bd.getPropertyValues();
        for (String s : encryptPropNames) {
            PropertyValue p = pv.getPropertyValue(s);
            //进行解密处理再覆盖原来的值
            assert p != null;
            String value = p.getValue().toString();
            if (p.getValue() instanceof TypedStringValue) {
                TypedStringValue typedStringValue = (TypedStringValue) p.getValue();
                value = typedStringValue.getValue();
            }
            String v = AES.decrypt(value);
            pv.add(s, v);
        }
    }

    /**
     * 第二种方式
     *
     * @param beanFactoryToProcess
     * @param valueResolver
     */
    @Override
    protected void doProcessProperties(ConfigurableListableBeanFactory beanFactoryToProcess, StringValueResolver valueResolver) {
        super.doProcessProperties(beanFactoryToProcess, valueResolver);
//        BeanDefinition bd = beanFactoryToProcess.getBeanDefinition("dataSource");
//        MutablePropertyValues pv =  bd.getPropertyValues();
//        for(String s:encryptPropNames){
//            PropertyValue p = pv.getPropertyValue(s);
        //进行解密处理再覆盖原来的值
//        assert p != null;
//        String value = p.getValue().toString();
//        if(p.getValue() instanceof TypedStringValue){
//            TypedStringValue typedStringValue = (TypedStringValue) p.getValue();
//            value = typedStringValue.getValue();
//        }
//        String v = AES.decrypt(value);
//        pv.add(s, v);
//        }
    }

    /**
     * 重写postProcessBeanFactory方法
     *
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        super.postProcessBeanFactory(beanFactory);
//        BeanDefinition bd = beanFactory.getBeanDefinition("dataSource");
//        MutablePropertyValues pv =  bd.getPropertyValues();
//        for(String s:encryptPropNames){
//            PropertyValue p = pv.getPropertyValue(s);
        //进行解密处理再覆盖原来的值
//        assert p != null;
//        String value = p.getValue().toString();
//        if(p.getValue() instanceof TypedStringValue){
//            TypedStringValue typedStringValue = (TypedStringValue) p.getValue();
//            value = typedStringValue.getValue();
//        }
//        String v = AES.decrypt(value);
//        pv.add(s, v);
//        }
    }
}
