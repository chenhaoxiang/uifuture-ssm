package com.uifuture.ssm.mq.aliyun.ons;


import java.util.Map;

/**
 * 消费者 阿里云配置属性
 */
public class ONSCustomerConnConfig {
    private String accessKey = "";
    private String secretKey = "";
    private Map<String, String> userConfig;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Map<String, String> getUserConfig() {
        return userConfig;
    }

    public void setUserConfig(Map<String, String> userConfig) {
        this.userConfig = userConfig;
    }
}
