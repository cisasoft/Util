package com.cisa.util;

import java.util.ArrayList;
import java.util.List;

import com.cisa.util.mail.MailHelper;


public class TestApplication {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

			// 参数传递
			List<String> to = new ArrayList<>();
			to.add("xiaolong.nie@cisasoft.cn");
			List<String> cc = new ArrayList<>();
			cc.add("dbjbdsjdisj2@163.com");
			cc.add("dbjbdsjdisj3@163.com");
			List<String> bcc = new ArrayList<>();
			bcc.add("dbjbdsjdisj3@163.com");

			List<String> attachment = new ArrayList<>();
			attachment.add("c:\\test\\w环境监测需求.docx");
			String subject = "ok";

			MailHelper.sendMails(to, cc, bcc, subject,
					"c://test//wkhtmltopdf2.html", attachment);
		
		
//		File f = new File("c://test//wkhtmltopdf2.html");
//		InputStream is = new FileInputStream(f);
//		BufferedReader in = new BufferedReader(new InputStreamReader(is,"utf-8"));
//		StringBuffer buffer = new StringBuffer();
//		String line = "";
//		while ((line = in.readLine()) != null) {
//			System.out.println(line);
//			buffer.append(line);
//		}
//		String body = new String(buffer.toString().getBytes());
//		System.out.println(body);
//		in.close();
		
		
//		// 属性集合对象
//		Properties prop = new Properties();
//		InputStream is = (InputStream) TestApplication.class.getClassLoader().getResourceAsStream("META-INF/mail.properties");
//        InputStreamReader isr = new InputStreamReader(is, "utf-8");
//        // 将属性文件流装载到Properties对象中
//        prop.load(isr);
//        isr.close();
//        is.close();
//        System.out.println("获取属性值：" + prop.getProperty("mail.host"));
		
//        FileInputStream fis = new FileInputStream("C:\\Users\\Xiaolong.Cisa\\Workspaces\\MyEclipse for Spring 2014\\Util\\src\\main\\resources\\META-INF\\mail.properties");// 属性文件输入流   
        
        
//        InputStreamReader isr = new InputStreamReader(fis, "utf-8");
        
//        prop.load(isr);// 将属性文件流装载到Properties对象中
//        isr.close();
//        fis.close();// 关闭流   
  
        // 获取属性值，sitename已在文件中定义   
//        System.out.println("获取属性值：sitename=" + prop.getProperty("user"));
//        // 获取属性值，country未在文件中定义，将在此程序中返回一个默认值，但并不修改属性文件   
//        System.out.println("获取属性值：country=" + prop.getProperty("mail.host"));
		
//        System.out.println(TestApplication.class.getResource("").toString()); 
        
        
  
        // 获取属性值，sitename已在文件中定义   
//        System.out.println("获取属性值：sitename=" + prop.getProperty("user"));
//        // 获取属性值，country未在文件中定义，将在此程序中返回一个默认值，但并不修改属性文件   
//        System.out.println("获取属性值：country=" + prop.getProperty("mail.host"));
		
//		Html2pdfHelper.getPdfWithItext("c://test//index.html","c://test//index.pdf");

		/*
		 * http://blog.csdn.net/o_darling/article/details/21160669
		 * http://blog.csdn.net/qiaqia609/article/details/11580589
		 * http://blog.csdn.net/ghsau/article/details/17839983
		 * http://www.w3cschool.cc/java/java-sending-email.html
		 * http://www.cnblogs.com/codeplus/archive/2011/10/30/2229391.html
		 * http://computerdragon.blog.51cto.com/6235984/1197390
		 * http://haolloyin.blog.51cto.com/1177454/354320
		 * 
		 * */
		
//		List<String> l  = FileHelper.getFileFromFolder("c://test");
//		
//		for(int i = 0; i<l.size();i++){
//			System.out.println(l.get(i));
//		}
		
//		ZipHelper.zipFile("c://test//w.pdf", "c://test//w.zip", "w1.pdf");
		
//		List<String> l1 = new ArrayList<>();
//		l1.add("c://test//w.pdf");
//		
//		l1.add("c://test//w环境监测需求.docx");
//		l1.add("c://test2//w环境监测需求.docx");
//		
//		l1.add("c://test//子文件1//e1.pdf");
//		l1.add("c://test//子文件1//e环境监测需求.xlsx");
//		
//		l1.add("c://test//子文件1//子文件2//p1.pdf");
//		l1.add("c://test//子文件1//子文件2//p环境监测需求.pptx");
//		
//		ZipHelper.zipFiles(l1, "c://test//mw.zip");
		
//		ZipHelper.zipFolders("c://test//", "c://test//ok1.zip");
//		ZipHelper.unZip("c://test//ok1.zip", "c://test//ok");
	}
}
