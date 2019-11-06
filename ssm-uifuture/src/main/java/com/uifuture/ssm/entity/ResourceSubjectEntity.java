package com.uifuture.ssm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.uifuture.ssm.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 资源专题表
 * </p>
 *
 * @author chenhx
 * @since 2019-09-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("resource_subject")
public class ResourceSubjectEntity extends BaseEntity {

    public static final String NAME = "name";
    public static final String IMAGE_URL = "image_url";
    public static final String DESCRIPTION = "description";
    public static final String CREATE_ID = "create_id";
    public static final String STATE = "state";
    private static final long serialVersionUID = 1L;
    /**
     * 专题名
     */
    private String name;
    /**
     * 专题封面图片地址
     */
    private String imageUrl;
    /**
     * 专题描述
     */
    private String description;
    /**
     * 创建者id
     */
    private Integer createId;
    /**
     * 专题状态 1-正常 0-失效
     */
    private Integer state;

}
