package com.uifuture.ssm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.uifuture.ssm.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 资源分类表。
 * </p>
 *
 * @author chenhx
 * @since 2019-09-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("resource_type")
public class ResourceTypeEntity extends BaseEntity {

    public static final String NAME = "name";
    public static final String PID = "pid";
    public static final String HIERARCHY = "hierarchy";
    private static final long serialVersionUID = 1L;
    /**
     * 资源类别名称、UI,JQurys、免费专区
     */
    private String name;
    /**
     * 父类的id，其父类也是资源类型
     */
    private Integer pid;
    /**
     * 当前层级
     */
    private Integer hierarchy;

}
