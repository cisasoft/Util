package com.cisa.util.mail;

import java.io.FileOutputStream;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * 纯文本文件发送样例
 * @author Xiaolong.Cisa
 *
 *
 *	dbjbdsjdisj1@163.com
 *	dbjbdsjdisj2@163.com
 *
 *	**********
 */
public class TextMessage {

	public static void main(String[] args) throws Exception{  
		
        String from = "dbjbdsjdisj1@163.com";  
        String to = "dbjbdsjdisj2@163.com";  
        String subject = "纯文本邮件";  
        String body = "纯文本邮件测试！！！";
          
        // 创建该邮件应用程序所需的环境信息以及会话信息  
        Session session = Session.getDefaultInstance(new Properties());  
          
        // 根据上面的 Session 实例创建 MimeMessage 实例，即一封邮件  
        MimeMessage msg = new MimeMessage(session);  
          
        // 设置发件人地址  
        msg.setFrom(new InternetAddress(from));  
          
        // 设置收件人地址  
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));  
          
        // 设置 E-mail 主题  
        msg.setSubject(subject);  
          
        // 设置发送时间  
        msg.setSentDate(new Date());  
          
        // 设置 E-mail 正文部分  
        msg.setText(body);  
          
        // 必须保存对该 MimeMessage 实例的更改  
        msg.saveChanges();  
          
        // 将 msg 对象中内容写入当前文件的textMail.eml文件中  
        msg.writeTo(new FileOutputStream("c://test//TextMessage.eml"));
    }
}
