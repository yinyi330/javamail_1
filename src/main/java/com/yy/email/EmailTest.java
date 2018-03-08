package com.yy.email;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.Date;
import java.util.Properties;

import static java.lang.System.out;

/**
 * Created by Administrator on 2018/3/8/008.
 */
public class EmailTest {
    //用户连接邮件服务器的参数（发送邮件的时候才需要）
    private Properties props;
    //根据参数配置，创建回话（为发送邮件做准备）
    private Session session;
    //创建邮件对象
    MimeMessage message;

    public EmailTest() {
        props = new Properties();
        session = Session.getInstance(props);
        message = new MimeMessage(session);
    }

    /**
     * @return void
     * @描述： 用于发送邮件的一个简单的小测试程序
     * @param: []
     * @author yinyi
     * @date 2018/3/8/008 16:13
     */
    public void sendEmail() {
        OutputStream outputStream = null;
        try {
            // 2. From: 发件人
            //    其中 InternetAddress 的三个参数分别为: 邮箱, 显示的昵称(只用于显示, 没有特别的要求), 昵称的字符集编码
            //    真正要发送时, 邮箱必须是真实有效的邮箱。
            message.setFrom(new InternetAddress("378175088@qq.com", "尹毅", "UTF-8"));
            // 3. To: 收件人
            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress("3306807806@qq.com", "USER_CC", "UTF-8"));
            //    To: 增加收件人（可选）
            message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress("dd@receive.com", "USER_DD", "UTF-8"));
            //    Cc: 抄送（可选）
            message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress("ee@receive.com", "USER_EE", "UTF-8"));
            //    Bcc: 密送（可选）
            message.setRecipient(MimeMessage.RecipientType.BCC, new InternetAddress("ff@receive.com", "USER_FF", "UTF-8"));
            // 4. Subject: 邮件主题
            message.setSubject("测试邮件", "UTF-8");
            // 5. Content: 邮件正文（可以使用html标签）
            message.setContent("TEST这是邮件正文。。。", "text/html;charset=UTF-8");
            // 6. 设置显示的发件时间
            message.setSentDate(new Date());
            // 7. 保存前面的设置
            message.saveChanges();
            outputStream = new FileOutputStream("MyEmail.eml");
            message.writeTo(outputStream);
            out.flush();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        EmailTest emailTest = new EmailTest();
        emailTest.sendEmail();
    }


}
