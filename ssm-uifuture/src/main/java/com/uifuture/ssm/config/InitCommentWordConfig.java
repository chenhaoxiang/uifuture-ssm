/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.config;

import com.uifuture.ssm.entity.SensitiveWordEntity;
import com.uifuture.ssm.entity.StopWordEntity;
import com.uifuture.ssm.service.SensitiveWordService;
import com.uifuture.ssm.service.StopWordService;
import com.uifuture.ssm.util.word.WordFilterUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * spring容器启动后，初始化数据
 *
 * @author chenhx
 * @version SsmServletContextListener.java, v 0.1 2019-09-23 02:15 chenhx
 */
@Component
@Slf4j
public class InitCommentWordConfig implements InitializingBean {
    @Autowired
    private StopWordService stopWordService;
    @Autowired
    private SensitiveWordService sensitiveWordService;

    @Override
    public void afterPropertiesSet() throws Exception {
        long s = System.currentTimeMillis();
        log.info("初始化数据开始");
        //初始化评论过滤词语

        //获取敏感词
        List<SensitiveWordEntity> sensitiveWordList = sensitiveWordService.list();
        //获取停顿词
        List<StopWordEntity> stopWordList = stopWordService.list();
        log.info("初始化评论过滤词语-敏感词数量:{},停顿词数量:{}", sensitiveWordList.size(), stopWordList.size());
        List<String> wordStr = new ArrayList<>();
        for (SensitiveWordEntity word : sensitiveWordList) {
            wordStr.add(word.getWord());
        }
        //加载敏感词
        WordFilterUtils.addSensitiveWord(wordStr);

        List<String> stopStr = new ArrayList<>();
        for (StopWordEntity word : stopWordList) {
            stopStr.add(word.getWord());
        }
        //加载停顿词
        WordFilterUtils.addStopWord(stopStr);

        long e = System.currentTimeMillis();
        log.info("初始化数据结束，一共消耗时间:{}毫秒 相当于 {}秒", (e - s), (e - s) / 1000.00);
    }

}
