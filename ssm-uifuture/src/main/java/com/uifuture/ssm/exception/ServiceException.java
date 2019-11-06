/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.exception;

import com.uifuture.ssm.base.BaseException;
import com.uifuture.ssm.enums.ResultCodeEnum;

/**
 * @author chenhx
 * @version ServiceException.java, v 0.1 2019-09-16 16:08 chenhx
 */
public class ServiceException extends BaseException {


    public ServiceException(Integer code, String message) {
        super(code, message);
    }

    public ServiceException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getValue(), resultCodeEnum.getName());
    }

}
