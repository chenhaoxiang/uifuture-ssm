/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.exception;

import com.uifuture.ssm.base.BaseException;
import com.uifuture.ssm.enums.ResultCodeEnum;

/**
 * @author chenhx
 * @version ParameterException.java, v 0.1 2019-11-07 20:20 chenhx
 */
public class ParameterException extends BaseException {


    public ParameterException(Integer code, String message) {
        super(code, message);
    }

    public ParameterException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getValue(), resultCodeEnum.getName());
    }

}
