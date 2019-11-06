/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.bo;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

/**
 * 排序条件
 *
 * @author chenhx
 * @version SortQueryBo.java, v 0.1 2019-09-20 17:32 chenhx
 */
@Data
public class SortQueryBo implements Serializable {

    private static final long serialVersionUID = 492019868197875577L;

    /**
     * 字段名
     */
    private String fieldName;

    /**
     * 排序类型
     */
    private SortTypeEnum sortTypeEnum;

    @Getter
    public enum SortTypeEnum {
        /**
         * 排序类型
         */
        ASC("asc", "正序"),
        DESC("desc", "倒序");
        private String code;
        private String desc;

        SortTypeEnum(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }
    }
}
