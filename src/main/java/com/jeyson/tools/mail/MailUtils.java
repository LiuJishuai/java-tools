package com.jeyson.tools.mail;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Properties;

/**
 * Created by  liujishuai
 * Create Date: 2017/7/12 19:34
 * Description: 邮件发送类
 * 需要的包  javax.mai.javax.mai-api和 com.sun.mai.javax.mail
 */
public class MailUtils implements Serializable {
    //邮箱服务器
    //private static final String SMTP_SERVER = "smtp.163.com";
    private static final String SMTP_SERVER = "smtp.126.com";
    //发送方用户名
    private static final String APIKey = "***@126.com";
    //发送方密码
    private static final String APISecret = "***";
    //发送来源
    private static final String EmailFrom = "***";

    /**
     * @param recipient      目标地址
     * @param subject        邮件主题
     * @param fileAttachment 附件
     * @param content        内容
     * @throws Exception
     */
    public static void sendEmail(String recipient, String subject, String fileAttachment, String content) throws Exception {
        String ttId = new Date().toString();
        Properties properties = new Properties();
        properties.put("mail.smtp.host", SMTP_SERVER);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "25");
        properties.put("mail.transport.protocol", "smtp");
        InternetAddress[] receiveAddresses = new InternetAddress[1];
        try {
            receiveAddresses[0] = new InternetAddress(recipient);
        } catch (AddressException e) {
            e.printStackTrace();
        }
        Session session = Session.getInstance(properties, new SmtpAuth());
        //session.setDebug(true);
        // session.setDebugOut(new PrintStream("SMTPLog.txt"));
        MimeMessage message = new MimeMessage(session);
        MimeMultipart multipart = new MimeMultipart();
        multipart.setSubType("alternative");

        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(content, "text/html; charset=utf-8");
        multipart.addBodyPart(bodyPart);
        if (fileAttachment != null) {
            DataSource dataSource = new FileDataSource(fileAttachment);
            String name = dataSource.getName();
            bodyPart = new MimeBodyPart();
            bodyPart.setDataHandler(new DataHandler(dataSource));
            bodyPart.setFileName(MimeUtility.encodeText(name));
            multipart.addBodyPart(bodyPart);
        }
        message.setSubject(subject);
        message.setContent(multipart);
        message.setFrom(new InternetAddress(EmailFrom));
        message.setRecipients(Message.RecipientType.TO, receiveAddresses);
        InternetAddress[] addresses = new InternetAddress[1];
        addresses[0] = new InternetAddress(recipient);
        message.setReplyTo(addresses);
        // message.addHeader("X-List", MimeUtility.encodeText("联系人列表名称"));
        message.addHeader("X-Campaign", MimeUtility.encodeText(ttId));
        message.addHeader("X-Qos", "transactional");
        try {
            Transport.send(message);

        } catch (Exception e) {
            throw new Exception("邮件发送失败,收件人:" + recipient + ",标题:" + subject);
        }

    }

    static class SmtpAuth extends Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(APIKey, APISecret);
        }
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 5; i++) {
            Thread.currentThread().sleep(2000L);
            sendEmail("jishuailiu@gamil.com", "京东物流提醒" + i, null, "我觉得上午去金沙滩好点，把你的感觉写下来吧");
        }

    }
}
