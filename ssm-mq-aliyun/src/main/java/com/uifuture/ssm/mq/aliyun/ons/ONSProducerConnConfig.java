package com.uifuture.ssm.mq.aliyun.ons;

import java.util.Map;

/**
 * 生产者配置
 */
public class ONSProducerConnConfig {
    /**
     * 阿里云账号key
     */
    private String accessKey = "";
    /**
     * 阿里云账号secret
     */
    private String secretKey = "";
    /**
     * 用户自定义配置
     */
    private Map<String, String> userConfig;
    private Integer sendMsgTimeoutMillis = null;

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

    public Integer getSendMsgTimeoutMillis() {
        return sendMsgTimeoutMillis;
    }

    public void setSendMsgTimeoutMillis(Integer sendMsgTimeoutMillis) {
        this.sendMsgTimeoutMillis = sendMsgTimeoutMillis;
    }

    public Map<String, String> getUserConfig() {
        return userConfig;
    }

    public void setUserConfig(Map<String, String> userConfig) {
        this.userConfig = userConfig;
    }
}
