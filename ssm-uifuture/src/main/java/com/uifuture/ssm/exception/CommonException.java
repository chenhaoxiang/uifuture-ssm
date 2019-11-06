/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.exception;

import com.uifuture.ssm.base.BaseException;
import com.uifuture.ssm.enums.ResultCodeEnum;

/**
 * @author chenhx
 * @version CommonException.java, v 0.1 2019-09-14 11:43 chenhx
 */
public class CommonException extends BaseException {


    public CommonException(Integer code, String message) {
        super(code, message);
    }

    public CommonException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getValue(), resultCodeEnum.getName());
    }

}
