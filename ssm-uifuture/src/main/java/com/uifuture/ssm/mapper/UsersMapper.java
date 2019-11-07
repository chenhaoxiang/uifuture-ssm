package com.uifuture.ssm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.uifuture.ssm.entity.UsersEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author chenhx
 * @since 2019-09-14
 */
public interface UsersMapper extends BaseMapper<UsersEntity> {

    /**
     * 操作UB
     *
     * @param usersId
     * @param ub
     */
    @Update("update users set ub=ub+#{ub} where id=#{usersId}")
    void operateUB(@Param("usersId") Integer usersId, @Param("ub") Integer ub);
}
