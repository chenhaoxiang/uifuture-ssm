package com.uifuture.ssm.email.impl;

import com.sun.mail.util.MailSSLSocketFactory;
import com.uifuture.ssm.email.EmailConfig;
import com.uifuture.ssm.email.SendEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Properties;
import java.util.concurrent.Callable;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2017/3/15.
 * Time: 下午 1:07.
 * Explain:发送邮件的实现类，此类继承了Thread，实现多线程发送邮件，调用start方法运行run函数
 */
public class SendEmailCallable implements Callable {
    private static Logger logger = LoggerFactory.getLogger(SendEmailCallable.class);
    private EmailConfig emailConfig;
    private SendEmail sendEmail;

    public SendEmailCallable() {
    }

    public SendEmailCallable(EmailConfig emailConfig, SendEmail sendEmail) {
        this.emailConfig = emailConfig;
        this.sendEmail = sendEmail;
    }

    @Override
    public Object call() throws Exception {
        // 跟smtp服务器建立一个连接
        Properties p = new Properties();
        // 设置邮件服务器主机名 // 指定邮件服务器，默认端口 25
        p.setProperty("mail.host", emailConfig.getMailHost());
        // 发送服务器需要身份验证 // 要采用指定用户名密码的方式去认证
        p.setProperty("mail.smtp.auth", "true");
        // 发送邮件协议名称
        p.setProperty("mail.transport.protocol", emailConfig.getMailProtocol());
        p.setProperty("mail.smtp.port", emailConfig.getPort());
        // 开启SSL加密，否则会失败
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
        } catch (GeneralSecurityException e1) {
            logger.error("开启SSL加密失败，出现GeneralSecurityException异常！给" + sendEmail.getName() + "," + sendEmail.getEmail() + "发送邮件失败！");
            logger.error("", e1);
            return null;
        }
        // 开启debug调试，以便在控制台查看
        //p.setProperty("mail.debug", "true");

        sf.setTrustAllHosts(true);
        p.put("mail.smtp.ssl.enable", "true");
        p.put("mail.smtp.ssl.socketFactory", sf);

        Session session = Session.getDefaultInstance(p, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名可以用邮箱的别名
                // 后面的字符是授权码！
                return new PasswordAuthentication(
                        emailConfig.getMainAdd(), emailConfig.getPassword());
            }
        });
        //session.setDebug(true);// 设置打开调试状态
        try {
            // 声明一个Message对象(代表一封邮件),从session中创建
            MimeMessage msg = new MimeMessage(session);
            // 邮件信息封装
            // 1发件人
            msg.setFrom(new InternetAddress(emailConfig.getMainAdd(), emailConfig.getMainName(), "UTF-8"));
            // 2收件人
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(sendEmail.getEmail()));
            // 3邮件内容:主题、内容
            msg.setSubject(emailConfig.getSubject());

            // StringBuilder是线程不安全的,但是速度快，这里因为只会有这个线程来访问，所以可以用这个
            //            发html格式的文本
            String sbd = ("<!DOCTYPE html><html><head><meta charset='UTF-8'>" +
                    "<title>邮箱激活</title>" +
                    "</head><body>" +
                    "<table style='background: #fff; border-collapse: collapse; border-spacing: 0; color: #222; font-size: 16px; height: 100%; margin: 0; padding: 0; width: 100%'bgcolor='#fff'>" +
                    "<tbody><tr><td style='-moz-hyphens: auto; -webkit-hyphens: auto; border-collapse: collapse !important; color: #222;  font-size: 16px; hyphens: auto; margin: 0; padding: 0; text-align: center; word-break: break-word'valign='top'align='center'>" +
                    "<center><table style='border-collapse: collapse; border-spacing: 0; font-size: 16px; line-height: 1.5; margin: 0 auto; max-width: 680px; min-width: 300px; width: 95%'>" +
                    "<tbody><tr><td style='-moz-hyphens: auto; -webkit-hyphens: auto; border-collapse: collapse !important; color: #222; font-family: font-size: 16px; hyphens: auto; margin: 0; padding: 0; word-break: break-word'>" +
                    "<hr style='background: #ddd; border: none; color: #ddd; height: 1px; margin: 20px 0 30px'>" +
                    "<table style='border-collapse: collapse; border-spacing: 0'width='100%'>" +
                    "<tbody><tr><td style='-moz-hyphens: auto; -webkit-hyphens: auto; border-collapse: collapse !important; color: #222; font-size: 16px; hyphens: auto; margin: 0; padding: 0; word-break: break-word'>" +
                    "<div><p style='color: #222;  font-size: 16px; margin: 0 0 10px; padding: 0'>" +
                    sendEmail.getName() + ",你好！" +
                    "</p><p style='color: #222;  font-size: 16px; margin: 0 0 10px; padding: 0'>" +
                    "感谢注册uifuture，您的验证码为" +
                    "</p><p style='color: #222; font-size: 16px; margin: 24px 0; padding: 0'>" +
                    "<a target='_blank'style='background: #83a198; border-radius: 4px; color: #fff; padding: 8px 16px; text-decoration: none; word-break: break-all'>" +
                    sendEmail.getCode() +
                    "</a></p><p style='color: #999999;  font-size: 12px; margin: 0 0 10px; padding: 0'>" +
                    "验证码10分钟内有效。如果你没有使用过" + emailConfig.getMainName() + "，请忽略此邮件。" +
                    "</p></div></td></tr></tbody></table>" +
                    "<hr style='background: #ddd; border: none; color: #ddd; height: 1px; margin: 20px 0'>" +
                    "<table style='border-collapse: collapse; border-spacing: 0'width='100%'>" +
                    "<tbody><tr><td colspan='2'style='-moz-hyphens: auto; -webkit-hyphens: auto; border-collapse: collapse !important; color: #222;font-size: 16px; hyphens: auto; margin: 0; padding: 0; word-break: break-word'align='center'>" +
                    "<p style='color: #999999; font-size: 12px; margin: 0 0 10px; padding: 0'>" +
                    "如有疑问请联系我们：" +
                    "<a href='mailto:" +
                    emailConfig.getServiceMail() +
                    "'style='color: #999999; text-decoration: underline'target='_blank'>" +
                    emailConfig.getServiceMail() +
                    "</a>" +
                    "</p></td></tr></tbody></table></td></tr></tbody></table></center></td></tr></tbody></table></body></html>");
            msg.setContent(sbd, "text/html;charset=utf-8");
            // 发送动作
            Transport.send(msg);
            logger.info("用户为:" + sendEmail.getName() + ",给" + sendEmail.getEmail() + "发送邮件成功...");
            return 1;
        } catch (MessagingException | UnsupportedEncodingException e) {
            logger.error("用户为:" + sendEmail.getName() + ",给" + sendEmail.getEmail() + "发送邮件失败，异常信息:" + e.getMessage());
            return "用户为:" + sendEmail.getName() + ",给" + sendEmail.getEmail() + "发送邮件失败，异常信息:" + e.getMessage();
        }
    }
}
