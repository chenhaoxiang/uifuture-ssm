package com.uifuture.ssm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.uifuture.ssm.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 支付类型表。
 * </p>
 *
 * @author chenhx
 * @since 2019-11-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("pay_type")
public class PayTypeEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 支付类型名称 例如:支付宝支付，微信支付，网银支付等
     */
    private String name;

    /**
     * 支付类型英文名，唯一
     */
    private String enName;


    public static final String NAME = "name";

    public static final String EN_NAME = "en_name";

}
