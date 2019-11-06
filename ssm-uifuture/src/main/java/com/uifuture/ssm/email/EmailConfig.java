package com.uifuture.ssm.email;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2017/3/18.
 * Time: 下午 1:08.
 * Explain:发件邮箱类需要实现的接口
 */
public interface EmailConfig {
    /**
     * @return 发件邮箱地址
     */
    String getMainAdd();

    /**
     * @return 发件人的名称
     */
    String getMainName();

    /**
     * @return 发件邮箱密码-也叫授权码
     */
    String getPassword();

    /**
     * @return 发件邮箱端口
     */
    String getPort();

    /**
     * @return 设置邮件服务器主机名
     */
    String getMailHost();

    /**
     * @return 发送邮件协议名称
     */
    String getMailProtocol();

    /**
     * @return 邮件内容:主题
     */
    String getSubject();

    /**
     * 项目地址
     *
     * @return
     */
    String getIndexAdd();

    /**
     * 客户邮件
     *
     * @return
     */
    String getServiceMail();
}
