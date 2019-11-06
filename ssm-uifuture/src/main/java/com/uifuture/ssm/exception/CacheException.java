package com.uifuture.ssm.exception;

import com.uifuture.ssm.base.BaseException;
import com.uifuture.ssm.enums.ResultCodeEnum;

public class CacheException extends BaseException {

    public CacheException(Integer code, String message) {
        super(code, message);
    }

    public CacheException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getValue(), resultCodeEnum.getName());
    }

}
