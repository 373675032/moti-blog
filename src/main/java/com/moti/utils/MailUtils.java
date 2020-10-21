package com.moti.utils;

import org.slf4j.Logger;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @ClassName: MailUtils
 * @Description: 邮箱工具类
 * @author: 莫提
 * @date 2020/9/21 20:13
 * @Version: 1.0
 **/
public class MailUtils {

    //邮件发送器
    private JavaMailSenderImpl mailSender;
    Logger logger = LogUtils.getInstance(MailUtils.class);

    public MailUtils(JavaMailSenderImpl mailSender){
        this.mailSender = mailSender;
    }

    /**
     * 发送简单邮件
     * @param title 邮件标题
     * @param text 邮件内容（简单邮件不支持HTML标签）
     * @param acceptEmail 接收方邮件
     */
    public void sendSimpleMailMessage(String title,String text,String acceptEmail){
        logger.warn("开始发送简单邮件...");
        logger.warn("mailSender对象为:"+mailSender);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(title);
        message.setText(text);
        message.setFrom("373675032@qq.com");
        message.setTo(acceptEmail);
        System.out.println(mailSender);
        logger.warn("message对象为:"+message);
        mailSender.send(message);
    }

    /**
     * 发送复杂邮件（支持邮件内容HTML解析）
     * @param title 邮件标题
     * @param text 邮件内容（简单邮件不支持HTML标签）
     * @param acceptEmail 接收方邮件
     * @throws MessagingException
     */
    public void sentComplexMailMessage(String title,String text,String acceptEmail){
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        try {
            helper.setSubject(title);
            helper.setText(text,true);
            helper.setFrom("373675032@qq.com");
            helper.setTo(acceptEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(mimeMessage);
    }

    public void sendReplyEmail(String title,String comment,String url,String acceptEmail){
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        try {
            helper.setSubject("作者回复了你在《"+title+"》下的留言");
            helper.setText("<div style='padding:25px;border:10px solid rgba(204, 204, 51, 0.8);height:300px;width:220px;font-size:14px;line-height:22px;'>\n" +
                    "    <h1 style='margin:0;padding:0;font-size:18px;'>作者回复</h1>\n" +
                    "    <hr>\n" +
                    "    <p style='margin:0;padding:0;text-indent:2em;'>"+comment+"</p>\n" +
                    "    <hr>\n" +
                    "    <a href='"+url+"' style='float: right;text-underline: none'>查看原文</a>" +
                    "</div>",true);
            helper.setFrom("373675032@qq.com");
            helper.setTo(acceptEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(mimeMessage);
    }

}
