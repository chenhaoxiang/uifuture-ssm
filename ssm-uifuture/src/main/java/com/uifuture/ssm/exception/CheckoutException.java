/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.exception;

import com.uifuture.ssm.base.BaseException;
import com.uifuture.ssm.enums.ResultCodeEnum;

/**
 * 校验异常
 *
 * @author chenhx
 * @version CheckoutException.java, v 0.1 2019-09-20 16:48 chenhx
 */
public class CheckoutException extends BaseException {


    public CheckoutException(Integer code, String message) {
        super(code, message);
    }

    public CheckoutException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getValue(), resultCodeEnum.getName());
    }

}
