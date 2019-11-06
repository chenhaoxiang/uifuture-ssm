package com.uifuture.ssm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.uifuture.ssm.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * <p>
 * 用户充值U币表，存放用户充值U币信息。
 * </p>
 *
 * @author chenhx
 * @since 2019-11-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("users_recharge_ub")
public class UsersRechargeUbEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 充值金额-单位元
     */
    private BigDecimal money;

    /**
     * 兑换的U币数量
     */
    private Integer ubNumber;

    /**
     * 充值的订单编号,格式为当前年月日时分秒毫秒+随机生成10位数字，如："20170228155316339*****"
     */
    private String orderNumber;

    /**
     * 支付类型id
     */
    private Integer payTypeId;

    /**
     * 用户支付详细信息id
     */
    private Integer usersPayId;


    public static final String USER_ID = "user_id";

    public static final String MONEY = "money";

    public static final String UB_NUMBER = "ub_number";

    public static final String ORDER_NUMBER = "order_number";

    public static final String PAY_TYPE_ID = "pay_type_id";

    public static final String USERS_PAY_ID = "users_pay_id";

}
