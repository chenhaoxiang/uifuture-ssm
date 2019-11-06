package com.uifuture.ssm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.uifuture.ssm.entity.ResourceEntity;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 资源表。 Mapper 接口
 * </p>
 *
 * @author chenhx
 * @since 2019-09-18
 */
public interface ResourceMapper extends BaseMapper<ResourceEntity> {

    /**
     * 增加属性的值
     *
     * @param
     */
    void addParamByToken(@Param("token") String token, @Param("param") String param, @Param("number") Integer number);
}
