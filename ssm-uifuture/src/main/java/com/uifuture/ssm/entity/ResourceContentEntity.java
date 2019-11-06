package com.uifuture.ssm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.uifuture.ssm.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author chenhx
 * @since 2019-09-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("resource_content")
public class ResourceContentEntity extends BaseEntity {

    public static final String CONTENT = "content";
    public static final String RESOURCE_TOKEN = "resource_token";
    private static final long serialVersionUID = 1L;
    /**
     * 资源描述与内容
     */
    private String content;
    /**
     * 资源token
     */
    private String resourceToken;

}
