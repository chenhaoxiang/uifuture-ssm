package com.uifuture.ssm.email;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2017/3/15.
 * Time: 下午 12:20.
 * Explain:用户发送邮件必须实现的接口
 */
public interface SendEmail {
    /**
     * 验证码
     *
     * @return
     */
    String getCode();

    /**
     * 用户的昵称
     *
     * @return
     */
    String getName();

    /**
     * 用户的邮箱，也就是收件邮箱
     *
     * @return
     */
    String getEmail();
}
