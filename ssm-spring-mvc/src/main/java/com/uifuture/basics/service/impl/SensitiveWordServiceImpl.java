/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.basics.service.impl;

import com.uifuture.basics.service.SensitiveWordService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 获取敏感词
 *
 * @author chenhx
 * @version SensitiveWordServiceImpl.java, v 0.1 2018-08-30 下午 8:21
 */
@Service
public class SensitiveWordServiceImpl implements SensitiveWordService {
    @Override
    public List<String> selectAllSensitiveWord() {
        //模拟从数据库中获取敏感词
        return Arrays.asList("骂人", "敏感词");
    }
}