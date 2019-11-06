/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.ssm.result;

import com.uifuture.ssm.enums.ResultCodeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一API响应结果封装
 *
 * @author chenhx
 * @version ResultModelAAA.java, v 0.1 2019-09-14 下午 2:53
 */
@Data
public class ResultModel<T> implements Serializable {
    private static final long serialVersionUID = 5698452005095426221L;
    private Integer code;
    private String message;
    private Boolean success = false;
    private T data;

    public ResultModel(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultModel(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResultModel(ResultCodeEnum resultCode) {
        this.code = resultCode.getValue();
        this.message = resultCode.getName();
    }

    public static ResultModel resultModel(Integer code, String message) {
        return new ResultModel(code, message);
    }

    public static ResultModel resultModel(ResultCodeEnum resultCode) {
        return new ResultModel(resultCode);
    }

    public static <T> ResultModel resultModel(Integer code, String message, T t) {
        return new ResultModel(code, message, t);
    }

    /**
     * 处理成功
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> ResultModel success(T t) {
        ResultModel resultModel = new ResultModel(ResultCodeEnum.SUCCESS);
        resultModel.setSuccess(true);
        resultModel.setData(t);
        return resultModel;
    }

    public static <T> ResultModel successNoData(String message) {
        ResultModel resultModel = new ResultModel(ResultCodeEnum.SUCCESS.getValue(), message);
        resultModel.setSuccess(true);
        return resultModel;
    }

    public static ResultModel success() {
        return success(null);
    }

    /**
     * 业务处理失败
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> ResultModel fail(T t) {
        ResultModel resultModel = new ResultModel(ResultCodeEnum.FAIL);
        resultModel.setData(t);
        return resultModel;
    }

    public static <T> ResultModel failNoData(String message) {
        return new ResultModel(ResultCodeEnum.FAIL.getValue(), message);
    }

    public static <T> ResultModel fail(String message, T date) {
        return new ResultModel(ResultCodeEnum.FAIL.getValue(), message, date);
    }

    public static ResultModel fail() {
        return fail(null);
    }

    public static ResultModel fail(ResultCodeEnum resultCodeEnum) {
        return fail("[" + resultCodeEnum.getValue() + "]" + resultCodeEnum.getName());
    }

    /**
     * 服务器内部错误
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> ResultModel error(T t) {
        ResultModel resultModel = new ResultModel(ResultCodeEnum.INTERNAL_SERVER_ERROR);
        resultModel.setData(t);
        return resultModel;
    }

    public static <T> ResultModel errorNoData(String message) {
        return new ResultModel(ResultCodeEnum.INTERNAL_SERVER_ERROR.getValue(), message);
    }

    public static ResultModel error() {
        return error(null);
    }

    /**
     * 认证不通过
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> ResultModel unAuthorized(T t) {
        ResultModel resultModel = new ResultModel(ResultCodeEnum.UNAUTHORIZED);
        resultModel.setData(t);
        return resultModel;
    }

    public static ResultModel unAuthorized() {
        return unAuthorized(null);
    }

}
