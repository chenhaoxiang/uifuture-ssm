/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.util;

import com.uifuture.ssm.enums.ResultCodeEnum;
import com.uifuture.ssm.exception.CheckoutException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * 参数校验，根据参数上的注解
 *
 * @author chenhx
 * @version ValidateUtils.java, v 0.1 2019-06-21 14:08 chenhx
 */
public class ValidateUtils {

    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> void validate(T object) {
        if (object == null) {
            throw new CheckoutException(ResultCodeEnum.PARAMETER_ERROR);
        }
        Set<ConstraintViolation<T>> constraintViolations = VALIDATOR.validate(object);
        //如果有验证信息，则抛出异常
        ConstraintViolation<T> constraintViolation = IteratorsUtils.getFirst(constraintViolations, null);
        if (constraintViolation != null) {
            throw new CheckoutException(ResultCodeEnum.PARAMETER_ERROR.getValue(), constraintViolation.getMessage());
        }

    }
}
