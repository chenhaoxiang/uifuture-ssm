/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.uifuture.ssm.base.page.Page;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;

/**
 * @author chenhx
 * @version BeanConvertUtils.java, v 0.1 2019-11-07 21:07 chenhx
 */
@Slf4j
public class BeanConvertUtils {
    /**
     * 由mp的分页数据转换为PageResp的分页数据
     *
     * @param page 源分页数据
     * @param list 数据
     * @return 的分页数据
     */
    public static <T> Page<T> pageConvert(IPage<?> page, List<T> list) {
        if (Objects.isNull(page)) {
            return null;
        }
        Page<T> pr = new Page<>();
        pr.setItems(list);
        pr.setPageSize((int) page.getSize());
        pr.setCurrentIndex((int) page.getCurrent());
        pr.setTotalNumber((int) page.getTotal());
        return pr;
    }
}
