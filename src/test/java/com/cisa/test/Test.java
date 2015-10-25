package com.cisa.test;

import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.cisa.util.file.FileHelper;
import com.cisa.util.time.DateHelper;

public class Test {
	
	public static void main(String[] args) throws Exception {
		
		//Office2pdfHelper.convert2PDF("C:\\test\\4.ppt", "c:/test31/ppt4.pdf");
//		Office2pdfHelper.convert2PDF("C:\\test\\1.pptx", "c:/test1/pptx.pdf");
		
//		Office2pdfHelper.convert2PDF("C:\\test\\2.xls", "c:/test31/xls.pdf");
//		Office2pdfHelper.convert2PDF("C:\\test\\2.xlsx", "c:/test31/xlsx.pdf");
//		
//		Office2pdfHelper.convert2PDF("C:\\test\\3.doc", "c:/test31/doc.pdf");
//		Office2pdfHelper.convert2PDF("C:\\test\\3.docx", "c:/test31/docx.pdf");
//		
//		Office2pdfHelper.convert2PDF("C:\\test\\2.txt", "c:/test31/txt.pdf");
		
		
		List<String> l = FileHelper.getFileFromFolder("c:/test/upload2");
		
		Iterator<String> lt = l.iterator();
		while(lt.hasNext()){
			String str =  lt.next();
			//System.out.println(str);
			File   f   =   new   File(str);
			String fname = f.getName();//.substring(18);
			String [] farr = fname.split("\\.");
			//System.out.println(farr[0]);
			
			long   size	=   f.length();	//   大小   bytes 
			long   modify	=   f.lastModified();	//   修改时间 
			Date dateOld = new Date(modify);
			String dt = DateHelper.dateToString(dateOld);
			//System.out.println(dt);
			
			System.out.println("insert into tmp_data values ('"+farr[0]+"','"+dt+"');");
			
		}
		
	}
}
