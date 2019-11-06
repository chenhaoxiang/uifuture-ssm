package com.uifuture.ssm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.uifuture.ssm.bo.UsersCommentQueryBo;
import com.uifuture.ssm.entity.UsersCommentEntity;

/**
 * <p>
 * 用户评论表 服务类
 * </p>
 *
 * @author chenhx
 * @since 2019-09-21
 */
public interface UsersCommentService extends IService<UsersCommentEntity> {

    /**
     * 软删评论
     *
     * @param commentId
     */
    void updateDeleteTimeById(Integer commentId);

    /**
     * 分页查询数据
     *
     * @param pageNum
     * @param pageSize
     * @param usersCommentQueryBo
     * @return
     */
    IPage<UsersCommentEntity> getPage(Integer pageNum, Integer pageSize, UsersCommentQueryBo usersCommentQueryBo);
}
