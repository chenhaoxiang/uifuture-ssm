package com.uifuture.ssm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.uifuture.ssm.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 资源标签关系表。
 * </p>
 *
 * @author chenhx
 * @since 2019-09-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("r_resource_tags")
public class RResourceTagsEntity extends BaseEntity {

    public static final String RESOURCE_ID = "resource_id";
    public static final String TAGS_ID = "tags_id";
    private static final long serialVersionUID = 1L;
    /**
     * 资源表id
     */
    private Integer resourceId;
    /**
     * 标签表id
     */
    private Integer tagsId;

}
