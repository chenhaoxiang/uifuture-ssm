package com.uifuture.ssm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.uifuture.ssm.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 敏感词过滤之-停顿词
 * </p>
 *
 * @author chenhx
 * @since 2019-09-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("stop_word")
public class StopWordEntity extends BaseEntity {

    public static final String WORD = "word";
    private static final long serialVersionUID = 1L;
    /**
     * 停顿词
     */
    private String word;

}
