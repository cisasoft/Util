package com.cisa.util;

import java.util.ArrayList;
import java.util.List;

import com.cisa.util.file.FileHelper;
import com.cisa.util.file.ZipHelper;

public class TestApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
		ZipHelper.unZip("c://test//ok1.zip", "c://test//ok");
	}
}
