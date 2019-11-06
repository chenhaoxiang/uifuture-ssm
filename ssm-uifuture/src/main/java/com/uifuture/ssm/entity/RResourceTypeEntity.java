package com.uifuture.ssm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.uifuture.ssm.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 资源与资源分类关系表
 * </p>
 *
 * @author chenhx
 * @since 2019-09-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("r_resource_type")
public class RResourceTypeEntity extends BaseEntity {

    public static final String RESOURCE_ID = "resource_id";
    public static final String RESOURCE_TYPE_ID = "resource_type_id";
    private static final long serialVersionUID = 1L;
    /**
     * 资源id
     */
    private Integer resourceId;
    /**
     * 资源分类id
     */
    private Integer resourceTypeId;

}
