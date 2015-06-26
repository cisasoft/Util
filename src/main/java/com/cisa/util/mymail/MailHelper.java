package com.cisa.util.mymail;

import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;

/**
 * 邮件发送要求
 * 
 * 收件人list      ToList
 * 抄送人list      CcList
 * 密送list			BccList
 * 附件list     AttachFileList
 * 主题                 Subject
 * 内容		Content
 * 
 * @author Xiaolong.Cisa
 *
 */
public class MailHelper {
	
	private String from;
	private List<String> toList;
	private List<String> ccList;
	private List<String> bccList;
	private List<String> attachFileList;
	private String smtpServer;
	
	public void sendMails(Authenticator auth){
		
		// 设置协议、是否身份验证、服务器等信息  
        Properties props = new Properties();  
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.host", smtpServer);
		
		
		
	}

}
