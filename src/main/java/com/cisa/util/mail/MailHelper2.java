package com.cisa.util.mail;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 * 发送邮件助手
 * 
 * @author Xiaolong.Cisa
 * @version 1.0
 */
public class MailHelper2 {

	/**
	 * 发送邮件
	 * 邮件服务器的配置文件mail.properties请放置于classpath的META-INF文件夹下
	 * 
	 * @param to
	 *            发送给，例如：123@123.com ...
	 * @param cc
	 *            抄送给，例如：456@123.com ...
	 * @param bcc
	 *            密送给，例如：789@123.com ...
	 * @param subject
	 *            主题
	 * @param bodyPath
	 *            邮件主题的html文件路劲，例如：c://test//test.html
	 * @param attachment
	 *            附件路径，例如：c://test//attachment.pdf
	 */
	public static void sendMails(List<String> to, List<String> cc,
			List<String> bcc, String subject, String bodyString,
			List<String> attachment) {
		try {
			// 属性集合对象
			Properties prop = new Properties();
			InputStream is = (InputStream) MailHelper2.class.getClassLoader()
					.getResourceAsStream("META-INF/mail.properties");
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			// 将属性文件流装载到Properties对象中
			prop.load(isr);
			isr.close();
			is.close();
			// 需要从property里面面截取的参数，设置协议、是否身份验证、服务器等信息
			@SuppressWarnings("unused")
			String Protocol = prop.getProperty("mail.transport.protocol");
			@SuppressWarnings("unused")
			String Auth = prop.getProperty("mail.smtp.auth");
			@SuppressWarnings("unused")
			String Host = prop.getProperty("mail.host");
			String user = prop.getProperty("user");
			String password = prop.getProperty("password");
			Authenticator auth = new SimpleAuthenticator(user, password);
			String from = user;
			Session session = Session.getInstance(prop, auth);
			// 启动JavaMail调试功能，可以返回与SMTP服务器交互的命令信息
			// 可以从控制台中看一下服务器的响应信息
			session.setDebug(true);

			// 设置发件人
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));

			// 设置收件人
			if (to != null) {
				Address[] addresses = new Address[to.size()];
				for (int i = 0; i < to.size(); i++) {
					addresses[i] = new InternetAddress(to.get(i));
				}
				msg.setRecipients(Message.RecipientType.TO, addresses);
			} else {
				msg.setRecipient(Message.RecipientType.TO, new InternetAddress(
						from));
			}

			// 设置抄送收件人
			if (cc != null) {
				Address[] addressesCC = new Address[cc.size()];
				for (int i = 0; i < cc.size(); i++) {
					addressesCC[i] = new InternetAddress(cc.get(i));
				}
				msg.setRecipients(Message.RecipientType.CC, addressesCC);
			}

			// 设置密送收件人
			if (bcc != null) {
				Address[] addressesBCC = new Address[bcc.size()];
				for (int i = 0; i < bcc.size(); i++) {
					addressesBCC[i] = new InternetAddress(bcc.get(i));
				}
				msg.setRecipients(Message.RecipientType.BCC, addressesBCC);
			}

			// 设置主题
			if (subject == null)
				subject = "No Subjects";
			msg.setSubject(subject, "UTF-8");

			// 将邮件组合成到一个"mixed"型的 MimeMultipart 对象
			MimeMultipart allPart = new MimeMultipart("mixed");

			// 创建邮件附件
			if (attachment != null) {
				List<MimeBodyPart> attachmentParts = createAttachments(attachment);
				for (int i = 0; i < attachmentParts.size(); i++) {
					allPart.addBodyPart(attachmentParts.get(i));
				}
			}

			// 创建邮件正文的html内容
			MimeBodyPart content = createContent(bodyString);

			allPart.addBodyPart(content);

			// 将上面混合型的 MimeMultipart 对象作为邮件内容并保存
			msg.setContent(allPart, "text/html;charset=utf-8");
			msg.saveChanges();

			/*
			 * 由于Session对象中注册了Authenticator子类对象，因此可以直接
			 * 从该Authenticator子类对象中获取登录的相关信息，故直接使用 Transport抽象类中静态
			 * send()方法来简化代码编写
			 */
			Transport.send(msg);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建MIME文件的HTML部分内容
	 * 
	 * @param bodyPath
	 *            HTML页面路径，假如是拼接的字符串请先用流写入到文件，统一使用无BOM的UTF-8进行编码，例如
	 *            "c://test//test.html"
	 * @return MimeBodyPart MIME体对象
	 * @throws Exception 向父调用抛出异常
	 */
	public static MimeBodyPart createContent(String bodyString) throws Exception {
		
		/*String in = B64.getBASE64(bodyString);*/
		// 用于保存最终版本
		MimeBodyPart contentBody = new MimeBodyPart();
		// 用于组合文本和图片，"related"型的MimeMultipart对象
		MimeMultipart contentMulti = new MimeMultipart("related");

		/*File f = new File(bodyPath);
		InputStream is = new FileInputStream(f);
		BufferedReader in = new BufferedReader(new InputStreamReader(is,
				"utf-8"));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while ((line = in.readLine()) != null) {
			System.out.println("out line: "+line);
			buffer.append(line);
		}*/
		/*String body = new String(buffer.toString().getBytes());
		in.close();*/
		/*String out = B64.getFromBASE64(in);*/

		// 添加html文件到MIME的body部分
		MimeBodyPart textBody = new MimeBodyPart();
		textBody.setContent(bodyString, "text/html;charset=utf-8");
		contentMulti.addBodyPart(textBody);

		// 图片部分可以增加参数String imgName，加假如不是网站的情况下
		// MimeBodyPart imgBody = new MimeBodyPart();
		// FileDataSource fds = new FileDataSource(imgName);
		// imgBody.setDataHandler(new DataHandler(fds));
		// imgBody.setContentID("img_id");
		// contentMulti.addBodyPart(imgBody);

		// 将上面"related"型的 MimeMultipart 对象作为邮件的正文
		contentBody.setContent(contentMulti);
		return contentBody;
	}

	/**
	 * 添加邮件附件
	 * 
	 * @param fileNames
	 *            附件路径list
	 * @return MimeBodyPart MIME体对象list
	 * @throws Exception 向父调用抛出异常
	 */
	public static List<MimeBodyPart> createAttachments(List<String> fileNames)
			throws Exception {
		List<MimeBodyPart> attachmentParts = new ArrayList<MimeBodyPart>();
		for (int i = 0; i < fileNames.size(); i++) {
			MimeBodyPart attachmentPart = new MimeBodyPart();
			File f = new File(fileNames.get(i));
			FileDataSource fds = new FileDataSource(f);
			attachmentPart.setDataHandler(new DataHandler(fds));
			// ecodeText解決中文编码问题
			attachmentPart.setFileName(MimeUtility.encodeText(f.getName()));
			attachmentParts.add(attachmentPart);
		}
		return attachmentParts;
	}

}
