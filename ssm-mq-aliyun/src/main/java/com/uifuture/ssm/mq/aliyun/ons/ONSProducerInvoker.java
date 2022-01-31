package com.uifuture.ssm.mq.aliyun.ons;


import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.SendResult;
import com.uifuture.ssm.mq.aliyun.util.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 生产者消息拦截
 */
public class ONSProducerInvoker implements InitializingBean, DisposableBean {

    private static final String DEFAULT_ENCODING = "UTF-8";
    private static Logger logger = LoggerFactory.getLogger(ONSProducerInvoker.class);
    private String producerId = "";
    private ONSProducerConnConfig config;
    private Producer producer;
    private boolean inited = false;
    private String encoding = DEFAULT_ENCODING;

    /**
     * 向所有tag发送消息
     *
     * @param topic
     * @param body
     * @param keys
     */
    public void send(String topic, Map<String, Object> body, String keys) {
        if (StringUtils.isEmpty(topic) || body == null) {
            return;
        }
        Message msg = new Message(topic, null, keys, com.alibaba.fastjson.JSON.toJSONString(body).getBytes(getEncoding()));
        send(msg);
    }

    /**
     * 向具体某一个tag发送消息
     *
     * @param topic
     * @param body
     * @param keys
     * @param tag
     */
    public void send(String topic, Map<String, Object> body, String keys, String tag) {
        if (StringUtils.isEmpty(topic) || body == null) {
            return;
        }
        Message msg = new Message(topic, tag, keys, com.alibaba.fastjson.JSON.toJSONString(body).getBytes(getEncoding()));
        send(msg);
    }

    /**
     * 向多个tag发送消息
     *
     * @param topic
     * @param body
     * @param keys
     * @param tags
     */
    public void send(String topic, Map<String, Object> body, String keys, String... tags) {
        if (StringUtils.isEmpty(topic) || body == null) {
            return;
        }
        List<String> tagList = Arrays.asList(tags);
        String orTag = CollectionUtils.combineListToString(tagList, "||");
        Message msg = new Message(topic, orTag, keys, com.alibaba.fastjson.JSON.toJSONString(body).getBytes(getEncoding()));
        send(msg);
    }

    private Charset getEncoding() {
        if (StringUtils.isEmpty(encoding)) {
            return Charset.forName(DEFAULT_ENCODING);
        } else {
            return Charset.forName(encoding);
        }
    }

    /**
     * 向多个tag发送消息
     *
     * @param topic
     * @param body
     * @param keys
     * @param tagList
     */
    public void send(String topic, Map<String, Object> body, String keys, List<String> tagList) {
        if (StringUtils.isEmpty(topic) || body == null) {
            return;
        }
        String orTag = CollectionUtils.combineListToString(tagList, "||");
        Message msg = new Message(topic, orTag, keys, com.alibaba.fastjson.JSON.toJSONString(body).getBytes(getEncoding()));
        send(msg);
    }

    public void send(String topic, Map<String, Object> body, String keys, Long delay) {
        if (StringUtils.isEmpty(keys)) {
            throw new IllegalArgumentException("keys不能为空");
        }
        Message msg = new Message(topic, null, keys, com.alibaba.fastjson.JSON.toJSONString(body).getBytes(getEncoding()));
        msg.setStartDeliverTime(getDelay(delay));
        send(msg);
    }

    public void send(String topic, Map<String, Object> body, String keys, String tag, Long delay) {
        if (StringUtils.isEmpty(keys)) {
            throw new IllegalArgumentException("keys不能为空");
        }
        if (StringUtils.isEmpty(topic) || body == null) {
            return;
        }
        Message msg = new Message(topic, tag, keys, com.alibaba.fastjson.JSON.toJSONString(body).getBytes(getEncoding()));
        msg.setStartDeliverTime(getDelay(delay));
        send(msg);
    }

    private Long getDelay(Long delay) {
        Date now = new Date();
        if (delay != null && delay > 0L) {
            return now.getTime() + delay * 1000;
        }
        return now.getTime();
    }

    private void send(Message msg) {
        try {

            SendResult sendResult = producer.send(msg);

            logger.debug("Aliyun ONS Send Result:" + sendResult);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public void dispose() {
        if (producer != null) {
            producer.shutdown();
            producer = null;
            logger.info("Aliyun ONS Producer [" + producerId + "] shutdown...");
        }
    }

    public void init() {
        if (inited) {
            return;
        }
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.GROUP_ID, producerId);
        properties.put(PropertyKeyConst.AccessKey, config.getAccessKey());
        properties.put(PropertyKeyConst.SecretKey, config.getSecretKey());
        // 可配置超时时间
        Integer sendMsgTimeoutMillis = config.getSendMsgTimeoutMillis();
        if (sendMsgTimeoutMillis != null && sendMsgTimeoutMillis > 0) {
            properties.put(PropertyKeyConst.SendMsgTimeoutMillis, sendMsgTimeoutMillis);
        }

        // 用户可以自定义配置
        Map<String, String> userConfig = config.getUserConfig();
        if (userConfig != null) {
            for (Map.Entry<String, String> entry : userConfig.entrySet()) {
                properties.put(entry.getKey(), entry.getValue());
            }
        }

        producer = ONSFactory.createProducer(properties);
        producer.start();
        logger.info("Aliyun ONS Producer [" + producerId + "] started...");
        inited = true;
    }

    public void setProducerId(String producerId) {
        this.producerId = producerId;
    }

    public void setConfig(ONSProducerConnConfig config) {
        this.config = config;
    }

    @Override
    public void destroy() throws Exception {
        dispose();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }
}
