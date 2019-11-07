package com.uifuture.ssm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.uifuture.ssm.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * <p>
 * 用户支付信息详情表
 * </p>
 *
 * @author chenhx
 * @since 2019-11-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("users_pay")
public class UsersPayEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 金额
     */
    private BigDecimal money;

    /**
     * 留言
     */
    private String info;

    /**
     * 通知邮箱
     */
    private String email;

    public static final String USERS_ID = "users_id";

    /**
     * pay_type的en_name，支付类型
     */
    private String payTypeEnName;

    /**
     * 支付标识
     */
    private String payNum;

    /**
     * 是否自定义输入，0否
     */
    private Integer custom;

    /**
     * 是否移动端，0否
     */
    private Integer mobile;

    /**
     * 用户支付设备信息
     */
    private String device;

    /**
     * 生成二维码编号标识token
     */
    private String tokenNum;

    /**
     * 通过审核的url
     */
    private String passUrl;

    /**
     * 操作后的url
     */
    private String backUrl;

    /**
     * 关闭交易的url
     */
    private String closeUrl;

    /**
     * 订单号
     */
    private String orderNumber;
    /**
     * 显示状态 0待审核 1通过 2驳回 3已扫码
     */
    private Integer state;


    public static final String MONEY = "money";

    public static final String INFO = "info";

    public static final String EMAIL = "email";

    public static final String STATE = "state";

    public static final String PAY_TYPE_EN_NAME = "pay_type_en_name";

    public static final String PAY_NUM = "pay_num";

    public static final String CUSTOM = "custom";

    public static final String MOBILE = "mobile";

    public static final String DEVICE = "device";

    public static final String TOKEN_NUM = "token_num";

    public static final String PASS_URL = "pass_url";

    public static final String BACK_URL = "back_url";

    public static final String CLOSE_URL = "close_url";

    public static final String ORDER_NUMBER = "order_number";
    /**
     * 用户id
     */
    private Integer usersId;

}
