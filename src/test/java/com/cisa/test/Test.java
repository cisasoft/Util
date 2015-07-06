package com.cisa.test;

import java.io.File;
import java.util.List;

import com.cisa.util.file.FileHelper;
import com.cisa.util.file.Office2pdfHelper;

public class Test {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<String> l = FileHelper.getFileFromFolder("c:\\test\\wxb\\upload");
		
		for(int i = 0;i<l.size();i++){
			File sf = new File(l.get(i));
			String filePdfTarget = "C:\\\\test\\\\wxb\\\\pdfview"+"/" + sf.getName();
			filePdfTarget=filePdfTarget.replaceAll(".doc",".pdf").replaceAll(".ppt", "").replaceAll(".xls",".pdf").replaceAll(".txt",".pdf").replaceAll(".docx",".pdf").replaceAll(".xlsx",".pdf").replaceAll(".pptx",".pdf");
			Office2pdfHelper oh = new Office2pdfHelper();
			System.loadLibrary("jacob-1.17-x86");
			oh.convert2PDF(sf.getAbsolutePath(),filePdfTarget);
			//System.out.println("/*转换成功："+filePdfTarget+"*/");
		}
		
		System.out.println("/*转换成功："+l.size()+"*/");
	}

}
