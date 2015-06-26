package com.cisa.util.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class UseAuthenticatorSender {

	private String smtpServer = "smtp.163.com";  
    private String from = "dbjbdsjdisj1@163.com";  
    private String to = "dbjbdsjdisj1@163.com";
    private String cc = "dbjbdsjdisj2@163.com";
    private String bcc = "dbjbdsjdisj3@163.com";
    private String subject = "使用Authenticator子类进行用户身份认证";  
    private String body = "使用Authenticator子类进行用户身份认证的测试！！！";  
 
    public void sendMails(Authenticator auth) throws Exception {  
        // 设置协议、是否身份验证、服务器等信息  
        Properties props = new Properties();  
        props.setProperty("mail.transport.protocol", "smtp");  
        props.setProperty("mail.smtp.auth", "true");  
        props.setProperty("mail.host", smtpServer);  
 
        // 通过传入的参数获得Authenticator子类对象
         
        Session session = Session.getInstance(props, auth);  
        session.setDebug(true);  
 
        MimeMessage msg = new MimeMessage(session);  
        msg.setFrom(new InternetAddress(from));  
        msg.setRecipient(RecipientType.TO, new InternetAddress(to));
        msg.setRecipient(RecipientType.BCC, new InternetAddress(bcc));
        msg.setRecipient(RecipientType.CC, new InternetAddress(cc));
        msg.setSubject(subject);  
        msg.setSentDate(new Date());  
        msg.setText(body);  
        msg.setContent("<h1>This is actual message</h1>", "text/html");
 
        msg.saveChanges();  
 
        /*  
         *  由于Session对象中注册了Authenticator子类对象，因此可以直接  
         *  从该Authenticator子类对象中获取登录的相关信息，故直接使用  
         *  Transport 抽象类中静态 send() 方法来简化代码编写  
         */ 
        Transport.send(msg);  
    }  
 
    // 测试  
    public static void main(String[] args) throws Exception {  
        UseAuthenticatorSender sender = new UseAuthenticatorSender();  
        // 这里体现了使用策略模式的好处，客户端可以选择使用  
        // 具体的哪一种身份验证方式来提交信息  
        Authenticator auth = new SimpleAuthenticator("dbjbdsjdisj1@163.com", "*******");  
        sender.sendMails(auth);  
    }  
	
	
}
