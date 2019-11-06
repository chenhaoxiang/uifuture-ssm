package com.uifuture.ssm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.uifuture.ssm.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 标签表。
 * </p>
 *
 * @author chenhx
 * @since 2019-09-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tags")
public class TagsEntity extends BaseEntity {

    public static final String NAME = "name";
    public static final String VISIT_TIMES = "visit_times";
    private static final long serialVersionUID = 1L;
    /**
     * 标签名
     */
    private String name;
    /**
     * 属于该标签的资源访问量
     */
    private Integer visitTimes;

}
