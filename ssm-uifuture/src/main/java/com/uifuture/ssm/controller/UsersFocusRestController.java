/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.uifuture.ssm.base.BaseController;
import com.uifuture.ssm.base.page.Page;
import com.uifuture.ssm.bo.UsersFocusQueryBo;
import com.uifuture.ssm.convert.UsersConvert;
import com.uifuture.ssm.dto.UsersFocusPageDTO;
import com.uifuture.ssm.entity.UsersEntity;
import com.uifuture.ssm.entity.UsersFocusEntity;
import com.uifuture.ssm.enums.ResultCodeEnum;
import com.uifuture.ssm.exception.CheckoutException;
import com.uifuture.ssm.result.ResultModel;
import com.uifuture.ssm.service.UsersFocusService;
import com.uifuture.ssm.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author chenhx
 * @version UsersFocusRestController.java, v 0.1 2019-09-20 00:12 chenhx
 */
@RestController
@RequestMapping("/usersFocus")
public class UsersFocusRestController extends BaseController {

    @Autowired
    private UsersFocusService usersFocusService;

    @Autowired
    private UsersService usersService;

    /**
     * 关注用户
     *
     * @return
     */
    @RequestMapping(value = "/focusOnTheUser", method = RequestMethod.POST)
    public ResultModel focusOnTheUser(Integer userId, HttpServletRequest request, HttpServletResponse response) {
        UsersEntity usersEntity = checkUserFocus(userId, request);

        //数据库已经有唯一建约束，不进行查询，直接进行插入数据
        UsersFocusEntity usersFocusEntity = new UsersFocusEntity();
        usersFocusEntity.setUserId(usersEntity.getId());
        usersFocusEntity.setFocusedUserId(userId);
        usersFocusService.save(usersFocusEntity);
        return ResultModel.success();
    }

    /**
     * 取消关注用户
     *
     * @return
     */
    @RequestMapping(value = "/cancelFocusOnTheUser", method = RequestMethod.POST)
    public ResultModel cancelFocusOnTheUser(Integer userId, HttpServletRequest request, HttpServletResponse response) {
        UsersEntity usersEntity = checkUserFocus(userId, request);

        usersFocusService.removeByUserIdAndFocusId(usersEntity.getId(), userId);
        return ResultModel.success();
    }

    /**
     * 校验用户
     *
     * @param userId  用户id
     * @param request 请求
     * @return 当前登录的用户
     */
    private UsersEntity checkUserFocus(Integer userId, HttpServletRequest request) {
        if (userId == null || userId < 0) {
            throw new CheckoutException(ResultCodeEnum.PARAMETER_ERROR);
        }
        //获取当前用户
        UsersEntity usersEntity = getLoginInfo(request);
        if (usersEntity == null) {
            throw new CheckoutException(ResultCodeEnum.USER_NOT_LOGGED);
        }
        //确保userId存在
        UsersEntity users = usersService.getById(userId);
        if (users == null) {
            throw new CheckoutException(ResultCodeEnum.PARAMETER_ERROR);
        }
        return usersEntity;
    }

    /**
     * 获取用户关注的用户列表，分页
     *
     * @return
     */
    @RequestMapping(value = "/pageList", method = RequestMethod.POST)
    public ResultModel pageList(Integer userId, Integer pageNum, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 20;
        }
        if (userId == null || userId < 0) {
            throw new CheckoutException(ResultCodeEnum.USER_NOT_LOGGED);
        }
        UsersFocusQueryBo usersFocusQueryBo = new UsersFocusQueryBo();
        usersFocusQueryBo.setUserId(userId);
        usersFocusQueryBo.buildQuery();
        IPage<UsersFocusEntity> entityIPage = usersFocusService.getPageByFocusId(pageNum, pageSize, usersFocusQueryBo);

        Page<UsersFocusPageDTO> usersFocusPageDTOPage = new Page<>();
        usersFocusPageDTOPage.setPageSize((int) entityIPage.getSize());
        usersFocusPageDTOPage.setCurrentIndex((int) entityIPage.getCurrent());
        usersFocusPageDTOPage.setTotalNumber((int) entityIPage.getTotal());

        List<UsersFocusPageDTO> usersFocusPageDTOS = new ArrayList<>();
        //批量查询用户信息
        List<Integer> usersId = new ArrayList<>();
        for (UsersFocusEntity record : entityIPage.getRecords()) {
            usersId.add(record.getUserId());
        }
        if (!CollectionUtils.isEmpty(usersId)) {
            Collection<UsersEntity> usersEntities = usersService.listByIds(usersId);
            usersFocusPageDTOS = UsersConvert.INSTANCE.entityToUsersFocusPageDtoList(usersEntities);
        }
        usersFocusPageDTOPage.setItems(usersFocusPageDTOS);
        return ResultModel.success(usersFocusPageDTOPage);
    }


    /**
     * 用户是否已经关注该用户
     *
     * @return
     */
    @RequestMapping(value = "/isFocus", method = RequestMethod.POST)
    public ResultModel isFocus(Integer userId, HttpServletRequest request, HttpServletResponse response) {
        UsersEntity usersEntity = checkUserFocus(userId, request);
        UsersFocusEntity usersFocusEntity = usersFocusService.getByUserIdAndFocusedId(usersEntity.getId(), userId);
        if (usersFocusEntity == null) {
            return ResultModel.success(false);
        }
        return ResultModel.success(true);
    }

}
