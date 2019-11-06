package com.uifuture.ssm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.uifuture.ssm.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author chenhx
 * @since 2019-09-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("users")
public class UsersEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * QQ互联中，获取的Access Token，得到对应用户身份的OpenID,OpenID是此网站上或应用中唯一对应用户身份的标识，网站或应用可将此ID进行存储，便于用户下次登录时辨识其身份，或将其与用户在网站上或应用中的原有账号进行绑定。  用户的ID，与QQ号码一一对应。
     */
    private String qqOpenid;

    /**
     * 用户主页图片地址-默认值为默认图片的地址
     */
    private String headImage;

    /**
     * 用户名(唯一而且不能有中文)-用户可以在注册后修改，修改一次90U币
     */
    private String username;

    /**
     * 个性签名
     */
    private String signature;

    /**
     * 密码(使用MD5+盐加密)
     */
    private String password;

    /**
     * 盐(密码加密时使用)
     */
    private String salt;

    /**
     * 手机号
     */
    private String mobilePhone;

    /**
     * 性别(默认为0)0-保密 1-男 2-女
     */
    private Integer sex;

    /**
     * 出生日期(默认为NULL)
     */
    private LocalDate birthday;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 微信号
     */
    private String weixin;

    /**
     * qq号
     */
    private String qq;

    public static final String QQ_OPENID = "qq_openid";

    /**
     * 当前账户可用U币(默认为0)
     */
    private Integer ub;

    /**
     * 支付宝账号(默认为NULL)
     */
    private String alipayAccountNumber;

    /**
     * 支付宝真实姓名(默认为NULL)
     */
    private String alipayRealName;

    /**
     * 实名认证状态 0-未实名认证 1-审核中 2-实名认证通过 3-实名认证失败
     */
    private Integer realNameState;

    /**
     * 描述
     */
    private String description;

    /**
     * 修改人的id
     */
    private Integer updateId;

    /**
     * 用户类型 0-用户 1-管理员 - 默认为0-也就是用户
     */
    private Integer type;

    /**
     * 用户状态(默认为1)1- 正常 0-被封号
     */
    private Integer state;

    /**
     * 创建者id
     */
    private Integer createId;

    public static final String HEAD_IMAGE = "head_image";
    public static final String USERNAME = "username";
    public static final String SIGNATURE = "signature";
    public static final String PASSWORD = "password";
    public static final String SALT = "salt";
    public static final String MOBILE_PHONE = "mobile_phone";
    public static final String SEX = "sex";
    public static final String BIRTHDAY = "birthday";
    public static final String EMAIL = "email";
    public static final String WEIXIN = "weixin";
    public static final String QQ = "qq";
    public static final String MAILBOX_STATE = "mailbox_state";
    public static final String UB = "ub";
    public static final String ALIPAY_ACCOUNT_NUMBER = "alipay_account_number";
    public static final String ALIPAY_REAL_NAME = "alipay_real_name";
    public static final String REAL_NAME_STATE = "real_name_state";
    public static final String DESCRIPTION = "description";
    public static final String UPDATE_ID = "update_id";
    public static final String TYPE = "type";
    public static final String STATE = "state";
    public static final String CREATE_ID = "create_id";
    /**
     * 邮箱激活状态 1-激活0-未激活
     */
    private Integer mailboxState;

}
