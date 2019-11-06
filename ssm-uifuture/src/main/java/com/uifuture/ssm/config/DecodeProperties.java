/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.ssm.config;


import com.uifuture.ssm.util.AES;

import java.io.IOException;
import java.util.Properties;

/**
 * @author chenhx
 * @version JDBCDecodeProperties.java, v 0.1 2019-09-10 下午 12:40
 */
public class DecodeProperties extends Properties {
    /**
     * 构造方法
     *
     * @param properties 属性配置的KV
     */
    public DecodeProperties(Properties properties) {
        try {
            this.load(DecodeProperties.class.getResourceAsStream("/config.properties"));
            for (String keys : properties.stringPropertyNames()) {
                for (String key : keys.split(",")) {
                    /**
                     * 进行解密并设置值
                     */
                    this.setProperty(key, AES.decrypt(this.getProperty(key)));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
