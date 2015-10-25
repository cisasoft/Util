package com.cisa.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.cisa.util.file.Html2pdfHelper;
import com.cisa.util.file.Office2pdfHelper;
import com.cisa.util.file.ZipHelper;
import com.cisa.util.image.ImageHelper;
import com.cisa.util.image.QRCodeHelper;
import com.cisa.util.mail.MailHelper;
import com.lowagie.text.Image;

public class Test {
	
	public static void main(String[] args) throws Exception {
		
//		Office2pdfHelper.convert2PDF("C:\\test\\4.ppt", "c:/test31/ppt4.pdf");
//		Office2pdfHelper.convert2PDF("C:\\test\\1.pptx", "c:/test31/pptx.pdf");
		
//		Office2pdfHelper.convert2PDF("C:\\test\\2.xls", "c:/test31/xls.pdf");
//		Office2pdfHelper.convert2PDF("C:\\test\\2.xlsx", "c:/test31/xlsx.pdf");
//		
//		Office2pdfHelper.convert2PDF("C:\\test\\3.doc", "c:/test31/doc.pdf");
//		Office2pdfHelper.convert2PDF("C:\\test\\3.docx", "c:/test31/docx.pdf");
//		
//		Office2pdfHelper.convert2PDF("C:\\test\\2.txt", "c:/test31/txt.pdf");
		
		
//		String s = "  123     456  7 9  ";
//		s = "abc";
//		s=s.trim().replaceAll(" +"," ");
//		System.out.println(s);
//		String s1[] = s.split(" ");
//		System.out.println(s1[0]);
		
		List<String> to = new ArrayList<>();
		//to.add("dbjbdsjdisj1@163.com");
		to.add("admin@teddygsp.com");
		to.add("dbjbdsjdisj1@163.com");
		
		MailHelper.sendMails(to, null, null, "et2c", "c:/test/test1.html", "test", null, "META-INF/mail.properties");
		
	}
}
