package com.uifuture.ssm.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.uifuture.ssm.base.BaseController;
import com.uifuture.ssm.base.page.Page;
import com.uifuture.ssm.bo.UsersCommentQueryBo;
import com.uifuture.ssm.convert.UsersCommentConvert;
import com.uifuture.ssm.dto.UsersCommentPageDTO;
import com.uifuture.ssm.entity.ResourceEntity;
import com.uifuture.ssm.entity.UsersCommentEntity;
import com.uifuture.ssm.entity.UsersEntity;
import com.uifuture.ssm.enums.DeleteEnum;
import com.uifuture.ssm.enums.ResultCodeEnum;
import com.uifuture.ssm.enums.UsersCommentEnum;
import com.uifuture.ssm.exception.CheckoutException;
import com.uifuture.ssm.req.UsersCommentReq;
import com.uifuture.ssm.result.ResultModel;
import com.uifuture.ssm.service.ResourceService;
import com.uifuture.ssm.service.UsersCommentService;
import com.uifuture.ssm.service.UsersService;
import com.uifuture.ssm.util.ValidateUtils;
import com.uifuture.ssm.util.XssUtils;
import com.uifuture.ssm.util.word.WordFilterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户评论表 前端控制器
 * </p>
 *
 * @author chenhx
 * @since 2019-09-21
 */
@RestController
@RequestMapping("/usersComment")
public class UsersCommentRestController extends BaseController {

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private UsersCommentService usersCommentService;

    @Autowired
    private UsersService usersServices;

    /**
     * 用户进行评论
     *
     * @return
     */
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public ResultModel comment(UsersCommentReq usersCommentReq, HttpServletRequest request, HttpServletResponse response) {
        //参数校验
        ValidateUtils.validate(usersCommentReq);
        //判断资源是否存在
        ResourceEntity resourceEntity = resourceService.getById(usersCommentReq.getResourceId());
        if (resourceEntity == null) {
            throw new CheckoutException(ResultCodeEnum.PARAMETER_ERROR);
        }
        UsersEntity usersEntity = getLoginInfo(request, true);

        UsersCommentEntity usersCommentEntity = UsersCommentConvert.INSTANCE.reqToEntity(usersCommentReq);
        usersCommentEntity.setUserId(usersEntity.getId());

        //  敏感词需要过滤
        String details = commentFilter(usersCommentEntity.getRealDetails());

        usersCommentEntity.setDetails(details);
        usersCommentEntity.setState(UsersCommentEnum.NORMAL.getValue());
        usersCommentService.save(usersCommentEntity);
        return ResultModel.success();
    }


    /**
     * 用户删除评论
     *
     * @return
     */
    @RequestMapping(value = "/deleteComment", method = RequestMethod.POST)
    public ResultModel deleteComment(Integer commentId, HttpServletRequest request, HttpServletResponse response) {
        if (commentId == null || commentId < 1) {
            throw new CheckoutException(ResultCodeEnum.PARAMETER_ERROR);
        }
        //获取登录用户
        UsersEntity usersEntity = getLoginInfo(request, true);
        //获取评论
        UsersCommentEntity usersCommentEntity = usersCommentService.getById(commentId);
        if (usersCommentEntity == null) {
            throw new CheckoutException(ResultCodeEnum.PARAMETER_ERROR);
        }
        if (!usersCommentEntity.getUserId().equals(usersEntity.getId())) {
            //无权限删除
            throw new CheckoutException(ResultCodeEnum.NO_PRIVILEGE);
        }
        //软删评论
        usersCommentService.updateDeleteTimeById(commentId);
        return ResultModel.success();
    }


    /**
     * 用户编辑评论
     *
     * @return
     */
    @RequestMapping(value = "/updateComment", method = RequestMethod.POST)
    public ResultModel updateComment(UsersCommentReq usersCommentReq, HttpServletRequest request, HttpServletResponse response) {
        //参数校验
        ValidateUtils.validate(usersCommentReq);
        //判断资源是否存在
        ResourceEntity resourceEntity = resourceService.getById(usersCommentReq.getResourceId());
        if (resourceEntity == null) {
            throw new CheckoutException(ResultCodeEnum.PARAMETER_ERROR);
        }
        //判断评论是否存在且未被删除
        UsersCommentEntity usersCommentEntity = usersCommentService.getById(usersCommentReq.getCommentId());
        if (usersCommentEntity == null) {
            throw new CheckoutException(ResultCodeEnum.PARAMETER_ERROR);
        }
        //校验评论未被禁用，且未被删除
        if (UsersCommentEnum.FORBIDDEN.getValue().equals(usersCommentEntity.getState()) || !DeleteEnum.NO_DELETE.getValue().equals(usersCommentEntity.getDeleteTime())) {
            throw new CheckoutException(ResultCodeEnum.STATUS_EXCEPTION);
        }

        UsersEntity usersEntity = getLoginInfo(request, true);
        //只有评论的创建者才能编辑评论
        if (!usersEntity.getId().equals(usersCommentEntity.getUserId())) {
            throw new CheckoutException(ResultCodeEnum.NO_PRIVILEGE);
        }

        UsersCommentEntity newUsersCommentEntity = new UsersCommentEntity();
        newUsersCommentEntity.setId(usersCommentEntity.getId());
        newUsersCommentEntity.setRealDetails(usersCommentEntity.getRealDetails());
        String details = commentFilter(usersCommentEntity.getRealDetails());

        usersCommentEntity.setDetails(details);
        usersCommentService.updateById(usersCommentEntity);
        return ResultModel.success();
    }

    /**
     * 敏感词过滤，XSS攻击防范
     *
     * @param realDetails
     * @return
     */
    private String commentFilter(String realDetails) {
        //  敏感词需要过滤
        String details = WordFilterUtils.doFilter(realDetails);
        //XSS过滤
        details = XssUtils.xssEncode(details);
        return details;
    }

    /**
     * 获取资源的评论 列表，分页
     *
     * @return
     */
    @RequestMapping(value = "/pageResourceList", method = RequestMethod.POST)
    public ResultModel pageResourceList(Integer resourceId, Integer pageNum, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 20;
        }
        if (resourceId == null || resourceId < 0) {
            throw new CheckoutException(ResultCodeEnum.PARAMETER_ERROR);
        }
        //获取资源
        ResourceEntity resourceEntity = resourceService.getById(resourceId);
        if (resourceEntity == null) {
            throw new CheckoutException(ResultCodeEnum.PARAMETER_ERROR);
        }

        UsersCommentQueryBo usersCommentQueryBo = new UsersCommentQueryBo();
        usersCommentQueryBo.setResourceId(resourceId);
        usersCommentQueryBo.buildQuery();
        IPage<UsersCommentEntity> entityIPage = usersCommentService.getPage(pageNum, pageSize, usersCommentQueryBo);

        Page<UsersCommentPageDTO> usersCommentDTOPage = new Page<>();
        usersCommentDTOPage.setPageSize((int) entityIPage.getSize());
        usersCommentDTOPage.setCurrentIndex((int) entityIPage.getCurrent());
        usersCommentDTOPage.setTotalNumber((int) entityIPage.getTotal());

        List<UsersCommentEntity> usersCommentEntities = entityIPage.getRecords();
        List<UsersCommentPageDTO> usersFocusPageDTOS = new ArrayList<>();

        List<Integer> userIds = new ArrayList<>();
        if (!CollectionUtils.isEmpty(usersCommentEntities)) {
            //获取用户id
            for (UsersCommentEntity usersCommentEntity : usersCommentEntities) {
                userIds.add(usersCommentEntity.getUserId());
            }
        }
        //查询用户信息
        Collection<UsersEntity> usersEntities = usersServices.listByIds(userIds);
        Map<Integer, UsersEntity> usersEntityMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(usersEntities)) {
            for (UsersEntity usersEntity : usersEntities) {
                usersEntityMap.put(usersEntity.getId(), usersEntity);
            }
        }

        for (UsersCommentEntity usersCommentEntity : usersCommentEntities) {
            UsersCommentPageDTO usersCommentPageDTO = UsersCommentConvert.INSTANCE.entityToDto(usersCommentEntity);
            usersCommentPageDTO.setUsername(usersEntityMap.get(usersCommentEntity.getUserId()).getUsername());
            usersFocusPageDTOS.add(usersCommentPageDTO);
        }

        usersCommentDTOPage.setItems(usersFocusPageDTOS);
        return ResultModel.success(usersCommentDTOPage);
    }

    /**
     * 获取用户的评论 列表，分页
     *
     * @return
     */
    @RequestMapping(value = "/pageUsersList", method = RequestMethod.POST)
    public ResultModel pageUsersList(Integer userId, Integer pageNum, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 20;
        }
        if (userId == null || userId < 0) {
            throw new CheckoutException(ResultCodeEnum.PARAMETER_ERROR);
        }
        //获取用户信息
        UsersEntity usersEntity = usersServices.getById(userId);
        if (usersEntity == null) {
            throw new CheckoutException(ResultCodeEnum.PARAMETER_ERROR);
        }

        UsersCommentQueryBo usersCommentQueryBo = new UsersCommentQueryBo();
        usersCommentQueryBo.setUserId(userId);
        usersCommentQueryBo.buildQuery();
        IPage<UsersCommentEntity> entityIPage = usersCommentService.getPage(pageNum, pageSize, usersCommentQueryBo);

        Page<UsersCommentPageDTO> usersCommentDTOPage = new Page<>();
        usersCommentDTOPage.setPageSize((int) entityIPage.getSize());
        usersCommentDTOPage.setCurrentIndex((int) entityIPage.getCurrent());
        usersCommentDTOPage.setTotalNumber((int) entityIPage.getTotal());

        List<UsersCommentPageDTO> usersFocusPageDTOS = UsersCommentConvert.INSTANCE.entityToPageList(entityIPage.getRecords());
        usersCommentDTOPage.setItems(usersFocusPageDTOS);
        return ResultModel.success(usersCommentDTOPage);
    }


}
