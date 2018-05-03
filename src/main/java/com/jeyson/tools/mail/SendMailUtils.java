package com.jeyson.tools.mail;

import com.jeyson.tools.file.*;
import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;
import java.io.*;
import java.util.Properties;

/**
 * @Author: liujishuai
 * @Time: 2018/2/2 10:06
 * @Description:
 */
public class SendMailUtils {
    /**
     * 发送邮件
     * @param nickName 发件人昵称
     * @param toEmail 收件人邮箱
     * @param replyEmail 回复邮箱
     * @param subject 主题
     * @param content 内容
     * @param attachmentFileName 附近名称
     * @param attachmentFile 附件内容
     * @param label
     * @return
     */
    private static boolean sendEmailSmtp(String nickName, String toEmail, String replyEmail, String subject, String content, String attachmentFileName, byte[] attachmentFile, String label) {
        try {
            final Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", "smtp.163.com");
            props.put("mail.smtp.port",25);
            props.put("mail.user", "***@163.com");
            props.put("mail.password", "****");
            Authenticator authenticator = new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    String userName = props.getProperty("mail.user");
                    String password = props.getProperty("mail.password");
                    return new PasswordAuthentication(userName, password);
                }
            };
            Session mailSession = Session.getInstance(props, authenticator);
            MimeMessage message = new MimeMessage(mailSession);
            InternetAddress form = new InternetAddress(MimeUtility.encodeText(nickName) + "<" + props.getProperty("mail.user") + ">");
            message.setFrom(form);
            InternetAddress replyTo = new InternetAddress(MimeUtility.encodeText(nickName) + "<" + replyEmail + ">");
            InternetAddress[] replyTos = new InternetAddress[]{replyTo};
            message.setReplyTo(replyTos);
            InternetAddress to = new InternetAddress(toEmail);
            message.setRecipient(MimeMessage.RecipientType.TO, to);
            message.setSubject(subject);
            Multipart mainPart = new MimeMultipart();
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(content, "text/html; charset=utf-8");
            mainPart.addBodyPart(messageBodyPart);
            if (attachmentFile != null && attachmentFile.length > 0) {
                messageBodyPart = new MimeBodyPart();
                ByteArrayDataSource fds = new ByteArrayDataSource(attachmentFile, "application/octet-stream");
                messageBodyPart.setDataHandler(new DataHandler(fds));
                messageBodyPart.setFileName(MimeUtility.encodeText(attachmentFileName, "utf-8", (String)null));
                mainPart.addBodyPart(messageBodyPart);
            }

            message.setContent(mainPart);
            Transport.send(message);
            System.out.println("sendEmailSmtp[" + toEmail + "] success");
            return true;
        } catch (Exception e) {
            System.out.println("sendEmailSmtp[" + toEmail + "] subject:[+subject+],has error:"+ e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        File file=new File("D:\\1.txt");
        try {
            FileInputStream inputStream=new FileInputStream(file);
            byte[] bytes= StreamUtils.transferInputStream2Bytes(inputStream);
            sendEmailSmtp("***","***","**","测试邮件","这是一封测试邮件","**.txt",bytes,"标签");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
