package com.uifuture.ssm.email;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2017/3/15.
 * Time: 下午 12:20.
 * Explain:用户发送邮件必须实现的接口
 */
public interface SendPayCheckEmail {
    /**
     * 收件邮箱
     *
     * @return
     */
    String getEmail();

    /**
     * 获取URL
     *
     * @return
     */
    String getUrl();

    /**
     * 用户信息
     *
     * @return
     */
    String getUserInfo();

    /**
     * 支付信息
     *
     * @return
     */
    String getPay();


}
