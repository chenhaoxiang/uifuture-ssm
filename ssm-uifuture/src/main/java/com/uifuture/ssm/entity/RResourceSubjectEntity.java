package com.uifuture.ssm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.uifuture.ssm.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 资源与专题关系表。
 * </p>
 *
 * @author chenhx
 * @since 2019-09-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("r_resource_subject")
public class RResourceSubjectEntity extends BaseEntity {

    public static final String RESOURCE_ID = "resource_id";
    public static final String SUBJECT_ID = "subject_id";
    private static final long serialVersionUID = 1L;
    /**
     * 资源表id
     */
    private Integer resourceId;
    /**
     * 专题表id
     */
    private Integer subjectId;

}
