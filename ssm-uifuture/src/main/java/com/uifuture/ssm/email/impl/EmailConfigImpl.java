package com.uifuture.ssm.email.impl;


import com.uifuture.ssm.email.EmailConfig;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2017/3/15.
 * Time: 下午 12:33.
 * Explain:我们的发件邮箱类实现类
 */
@Component
@Data
public class EmailConfigImpl implements EmailConfig {
    /**
     * 发件邮箱地址
     */
    @Value("${email.mail.add}")
    private String mainAdd;
    /**
     * 发件人的名称
     */
    @Value("${email.mail.name}")
    private String mainName;
    /**
     * 发件邮箱密码-也叫授权码
     */
    @Value("${email.mail.password}")
    private String password;
    /**
     * 发件邮箱端口
     */
    @Value("${email.mail.port}")
    private String port;
    /**
     * 设置邮件服务器主机名
     */
    @Value("${email.mail.host}")
    private String mailHost;
    /**
     * 发送邮件协议名称
     */
    @Value("${email.mail.protocol}")
    private String mailProtocol;
    /**
     * 邮件内容:主题
     */
    @Value("${email.subject}")
    private String subject;
    /**
     * 网站地址
     */
    @Value("${email.index.mail.add}")
    private String indexAdd;
    /**
     * 客服邮箱
     */
    @Value("${email.service.mail}")
    private String serviceMail;
}
