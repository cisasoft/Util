package com.cisa.test;


public class MailTest {
    /**
     * @param args
     */
    public static void main(String[] args) {
        //这个类主要是设置邮件 
        MailSenderInfo mailInfo = new MailSenderInfo();  
        mailInfo.setMailServerHost("mail.teddygsp.com");  
        mailInfo.setMailServerPort("465");  
        mailInfo.setValidate(true);  
        mailInfo.setUserName("admin@teddygsp.com");  
        mailInfo.setPassword("teddy&china");//您的邮箱密码  
        mailInfo.setFromAddress("admin@teddygsp.com");  
        mailInfo.setToAddress("dbjbdsjdisj1@163.com");  
        mailInfo.setSubject("设置邮箱标题 如http://www.guihua.org 中国网");  
        mailInfo.setContent("<h2><font color=red>这倒霉孩子</font></h2>");  
           //这个类主要来发送邮件 
        SimpleMailSender sms = new SimpleMailSender(); 
        //sms.sendTextMail(mailInfo);//发送文体格式  
        sms.sendHtmlMail(mailInfo);//发送html格式
    }
}