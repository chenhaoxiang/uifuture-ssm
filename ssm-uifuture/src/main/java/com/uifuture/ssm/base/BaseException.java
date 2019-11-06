/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.base;

import com.uifuture.ssm.enums.ResultCodeEnum;
import lombok.Data;

/**
 * @author chenhx
 * @version BaseException.java, v 0.1 2019-09-14 11:44 chenhx
 */
@Data
public class BaseException extends RuntimeException {
    protected Integer code;

    protected String message;


    public BaseException(Integer code, String message) {
        super("code:" + code + ",message:" + message);
        this.code = code;
        this.message = message;
    }


    public BaseException(ResultCodeEnum resultCodeEnum) {
        super("code:" + resultCodeEnum.getValue() + ",message:" + resultCodeEnum.getName());
        this.code = resultCodeEnum.getValue();
        this.message = resultCodeEnum.getName();
    }

}
