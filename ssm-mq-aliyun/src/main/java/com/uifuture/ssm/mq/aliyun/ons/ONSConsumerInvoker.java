package com.uifuture.ssm.mq.aliyun.ons;


import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Consumer;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.uifuture.ssm.mq.aliyun.ConsumeResult;
import com.uifuture.ssm.mq.aliyun.MQConsumer;
import com.uifuture.ssm.mq.aliyun.util.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class ONSConsumerInvoker implements InitializingBean, DisposableBean {
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static Logger logger = LoggerFactory.getLogger(ONSConsumerInvoker.class);
    private ONSCustomerConnConfig config;
    private MQConsumer reciver = null;
    private Consumer consumer;
    private boolean inited = false;
    private String encoding = "";
    private String consumerId = "";
    private String topic = "";
    private String tag = "*";
    private boolean enabled = true;
    private Map<String, MQConsumer> tags = new HashMap<>();

    public void dispose() throws Exception {
        if (consumer != null) {
            //先取消订阅
            consumer.unsubscribe(topic);
            consumer.shutdown();
            consumer = null;
            logger.info("Aliyun ONS [" + topic + "." + consumerId + "] consumer shutdown...");
            inited = false;
        }
    }

    public void init() throws Exception {
        if (inited || !enabled) {
            return;
        }
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.GROUP_ID, consumerId);
        properties.put(PropertyKeyConst.AccessKey, config.getAccessKey());
        properties.put(PropertyKeyConst.SecretKey, config.getSecretKey());
        // 用户可以自定义配置
        Map<String, String> userConfig = config.getUserConfig();
        if (userConfig != null) {
            for (Entry<String, String> entry : userConfig.entrySet()) {
                properties.put(entry.getKey(), entry.getValue());
            }
        }
        // 用户可以自定义配置 end
        consumer = ONSFactory.createConsumer(properties);
        listenMessage();
        inited = true;
    }

    private void listenMessage() {
        if (CollectionUtils.isNotEmpty(tags)) {
            List<String> keyList = new ArrayList<>(tags.keySet());
            String orTag = CollectionUtils.combineListToString(keyList, "||");
            consumer.subscribe(topic, orTag, new MQMessageListener());
        } else {
            consumer.subscribe(topic, tag, new MQMessageListener());
        }
        consumer.start();
        logger.info("Aliyun ONS consumer [" + topic + "." + consumerId + "] started...");
    }

    private String getEncoding() {
        if (StringUtils.isEmpty(encoding)) {
            return DEFAULT_ENCODING;
        } else {
            return encoding;
        }
    }

    public void setConsumerId(String consumerId) {
        this.consumerId = consumerId;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setTags(Map<String, MQConsumer> tags) {
        Map<String, MQConsumer> combinedMap = new HashMap<>();
        if (CollectionUtils.isNotEmpty(tags)) {
            for (String tag : tags.keySet()) {
                MQConsumer value = tags.get(tag);
                if (tag.contains("|")) {
                    String[] tagGroup = tag.split("\\|");
                    for (String each : tagGroup) {
                        combinedMap.put(each, value);
                    }
                } else {
                    combinedMap.put(tag, value);
                }
            }
        }
        this.tags = combinedMap;
    }

    /**
     * Setter method for property <tt>reciver</tt>.
     *
     * @param reciver value to be assigned to property reciver
     */
    public void setReciver(MQConsumer reciver) {
        this.reciver = reciver;
    }

    /**
     * Setter method for property <tt>config</tt>.
     *
     * @param config value to be assigned to property config
     */
    public void setConfig(ONSCustomerConnConfig config) {
        this.config = config;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

    @Override
    public void destroy() throws Exception {
        dispose();
    }

    class MQMessageListener implements MessageListener {

        @Override
        public Action consume(Message msg, ConsumeContext context) {
            try {
                //反序列化对象
                Map<String, Object> params = new HashMap<>();

                String msgStr = new String(msg.getBody(), getEncoding());
                com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(msgStr);
                if (jsonObject != null && !jsonObject.isEmpty()) {
                    for (Entry<String, Object> entry : jsonObject.entrySet()) {
                        params.put(entry.getKey(), entry.getValue());
                    }
                }

                if (CollectionUtils.isNotEmpty(tags)) {
                    String tag = msg.getTag();
                    MQConsumer mqReciver = tags.get(tag);
                    if (mqReciver == null) {
                        String errStr = "消息配置错误, 没有设置接收者, where tag=" + tag;

                        logger.error(errStr);
                        return Action.CommitMessage;
                    }
                    return ComsumeResultAdapter.getResult(mqReciver.onRecived(params));
                }
                if (reciver == null) {
                    String errStr = "消息配置错误, 没有设置接收者";
                    logger.error(errStr);
                    return Action.CommitMessage;
                }

                ConsumeResult result = reciver.onRecived(params);
                return ComsumeResultAdapter.getResult(result);
                //修改为Throwable ，防止error的异常没被抓到  见https://jira.souche-inc.com/browse/OPTRACE-38
            } catch (Throwable e) {
                //往业务层继续抛
                logger.error("执行消息接受失败", e);
                return Action.CommitMessage;
            }
        }

    }
}
