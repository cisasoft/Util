package com.cisa.util.mail;

import java.io.FileOutputStream;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class WithPictureMessage {

	public static void main(String[] args) throws Exception{  
		 
        String from = "xiaolong.nie@cisasoft.cn";  
        String to = "xiaolong.nie@cisasoft.cn";  
        String subject = "创建一个内含图片的邮件！";  
        String body = "<h4>内含图片的邮件测试！！！</h4> " +  
        "<a href = http://haolloyin.blog.51cto.com/> 你好啊 hello </a></br></br>" +   
        "<p><a href = http://www.apache.org/img/asf_logo.png /></p>" +  
//        "<img src = \"cid:haolloyin_logo_jpg\"></a>";  
		"<img src = \"http://www.apache.org/img/asf_logo.png\"></a>";
        /*  
         *  上面的 cid 是指 MIME 协议中的 Content-ID 头，它是  
         *  对于文件资源的唯一标识符，下面的 setContentID()方法  
         *  中传入的 ID 号就必须与这个haolloyin_logo_jpg相一致  
         */ 
          
        // 创建该邮件应用程序所需的环境信息以及会话信息  
        Session session = Session.getDefaultInstance(new Properties());  
          
        // 以下几句是设置邮件的基本信息  
        MimeMessage msg = new MimeMessage(session);       
        msg.setFrom(new InternetAddress(from));       
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));  
        msg.setSubject(subject);  
        msg.setSentDate(new Date());  
          
        /*  
         *  创建一个子类型为 “related” 的 MIME 消息组合对象，  
         *  其实 MimeMultipart 类还有另外两种子类型，请自行  
         *  查阅并比较其中的适用场景  
         */ 
        MimeMultipart multipart = new MimeMultipart("related");  
          
        /*  
         * 创建一个表示 HTML 正文部分的 MimeBodyPart 对象，   
         * 并加入 到上一语句创建的 MimeMultiPart 对象中，  
         * 由于 HTML 正文中包含中文，故下面应该指定字符集为 gbk 或 gb2312  
         */ 
        MimeBodyPart htmlBodyPart = new MimeBodyPart();  
        //htmlBodyPart.setContent(body, "text/html;charset=gbk");  
        htmlBodyPart.setContent(body, "text/html;charset=utf-8");  
        multipart.addBodyPart(htmlBodyPart);  
          
        /*  
         *  创建一个表示图片文件的 MimeBodyPart 对象，  
         *  并加入 到上面所创建的 MimeMultiPart 对象中，  
         *  使用 JAF 框架中的 FileDataSource 类获取图片文件源，  
         *  它能够自动获知文件的 MIME 格式并设置好消息头  
         */ 
        MimeBodyPart jpgBodyPart = new MimeBodyPart();  
        FileDataSource fds = new FileDataSource("c:\\test\\snap.jpg");  
        jpgBodyPart.setDataHandler(new DataHandler(fds));  
        jpgBodyPart.setContentID("haolloyin_logo_jpg");  
          
        multipart.addBodyPart(jpgBodyPart);  
          
        /*  
         *  这里不用再设置字符集了，因为上面的 HTML MimeBodyPart 对象中  
         *  已经设置了，而图片文件的因为使用了 DataSource 对象，即 JAF 框架  
         *  中的具体数据处理操作类，能够自动判断获知数据的 MIME 类型  
         */ 
        msg.setContent(multipart,"text/html");  
          
        // 保存对邮件的修改并写入文件中  
        msg.saveChanges();        
        msg.writeTo(new FileOutputStream("c:\\test\\withPictureMail.eml"));  
    }  
	
}
