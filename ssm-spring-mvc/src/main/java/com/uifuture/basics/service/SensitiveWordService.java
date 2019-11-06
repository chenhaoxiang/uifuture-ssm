/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.basics.service;

import java.util.List;

/**
 * 获取敏感词列表
 *
 * @author chenhx
 * @version SensitiveWord.java, v 0.1 2018-08-30 下午 8:20
 */
public interface SensitiveWordService {
    /**
     * 获取所有的敏感词
     *
     * @return
     */
    List<String> selectAllSensitiveWord();
}
