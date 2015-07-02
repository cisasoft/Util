package com.cisa.util;

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.cisa.util.encrypt.RandomHelper;
import com.cisa.util.file.FileHelper;
import com.cisa.util.file.Office2pdfHelper;

public class TestApplication {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Set<String> us = new HashSet<String>();
		Set<String> usp = new HashSet<String>();
		//dp收集父父目录和父目录的关系
		Set<String> dp = new HashSet<String>();
		List<String> l = FileHelper.getFileFromFolder("c:\\test\\wxb\\needsupload");
		//文件->文件父目录->父目录的父目录
		String [][] strl = new String[l.size()][3];
		
		for (int i = 0; i < l.size(); i++){
			File f = new File(l.get(i));
			File fp = f.getParentFile();
			File fpp = fp.getParentFile();
			if(fp.isDirectory()){
				//System.out.println(f.getAbsolutePath()+"-->"+fp.getName()+"-->"+fpp.getName());
			}
			us.add(fp.getName());
			usp.add(fpp.getName());
			dp.add(fpp.getName()+File.separator+fp.getName());
			strl[i][0] = f.getAbsolutePath();
			strl[i][1] = fp.getName();
			strl[i][2] = fpp.getName();
		}
		
		Iterator<String> it = us.iterator();
		while (it.hasNext()){
			String is = it.next();
			//System.out.println("需要创建的文件夹名字为 "+is);
			String path = "C:\\\\apache\\\\tomcat-8.0.21\\\\webapps\\\\wxb\\\\upload/"+is;//"c:\\test\\wxb\\upload/"+is;
			File fm = new File(path);
			//System.out.println("创建文件夹结果为 "+is+" "+fm.mkdirs());
			fm.mkdirs();
		}
		
		Iterator<String> itp = usp.iterator();
		while (itp.hasNext()){
			String is = itp.next();
			//System.out.println("需要创建的父文件夹名字为 "+is);
			String path = "C:\\\\apache\\\\tomcat-8.0.21\\\\webapps\\\\wxb\\\\upload/"+is;//"c:\\test\\wxb\\upload/"+is;
			File fm = new File(path);
			//System.out.println("创建文件夹结果为 "+is+" "+fm.mkdirs());
			fm.mkdirs();
		}
		
		int count = 0;
		itp = usp.iterator();
		String [][] ps = new String [usp.size()][2];
		for(int j = 1 ; itp.hasNext(); j++){
			count +=1;
			String is = itp.next();
			String path = "C:\\\\apache\\\\tomcat-8.0.21\\\\webapps\\\\wxb\\\\upload/";//"c:\\\\test\\\\wxb\\\\upload/";
			String sql_info_file_catalog = 
							"INSERT INTO info_file_catalog" +
							" (id, parent_id, serial_code, name, levelkey, url)" + 
							" VALUES" + 
							" ("+count+"," + 
							" 0, " + 
							" '"+count+"'," + 
							" '"+is+"'," + 
							" '00"+count+"'," + 
							" '"+path+is+"');";

			System.out.println(sql_info_file_catalog);
			ps[j-1][0]= Integer.toString(j);
			ps[j-1][1]= is;
		}
		
		Iterator<String> itdp = dp.iterator();
		String [][] dps = new String[dp.size()][3];
		for(int j = 1;itdp.hasNext();j++){
			int pid = 0;
			count +=1;
			String path = "C:\\\\apache\\\\tomcat-8.0.21\\\\webapps\\\\wxb\\\\upload/";//"c:\\\\test\\\\wxb\\\\upload/";
			String is = itdp.next();
			for(int m = 0; m<ps.length;m++){
				if(is.contains(ps[m][1]))
					pid = Integer.parseInt(ps[m][0]);
			}
			String js = "";
			if (j<10) js = "00"+j;
			if (j>=10) js = "0" +j;
			String sql_info_file_catalog = 
					"INSERT INTO info_file_catalog" +
					" (id, parent_id, serial_code, name, levelkey, url)" + 
					" VALUES" + 
					" ("+count+"," + 
					" "+pid+"," + 
					" '"+count+"'," + 
					" '"+is.split("\\"+File.separator)[1]+"'," + 
					" '00"+pid+js+"'," + 
					" '"+path+is.split("\\"+File.separator)[1]+"');";
			
			System.out.println(sql_info_file_catalog);
			dps[j-1][0]= Integer.toString(count);
			dps[j-1][1] = is;
			dps[j-1][2] = is.split("\\"+File.separator)[1];
		}
		
		
		for (int i = 0;i< strl.length;i++){
			String fileSource = strl[i][0];
			File sf = new File(strl[i][0]);
			String ran18 = RandomHelper.getRandom(18);
			String fileTarget = "C:\\\\apache\\\\tomcat-8.0.21\\\\webapps\\\\wxb\\\\upload/"+strl[i][1]+"/" + ran18 + sf.getName();
					//"c:\\\\test\\\\wxb\\\\upload/"+strl[i][1]+File.separator+RandomHelper.getRandom(18)+sf.getName();
			FileHelper.copyFile(strl[i][0], fileTarget);
			
			
			/*String filePdfTarget = "C:\\\\apache\\\\tomcat-8.0.21\\\\webapps\\\\wxb\\\\pdfview"+"/" + ran18 + sf.getName();
			filePdfTarget=filePdfTarget.replaceAll(".doc",".pdf").replaceAll(".ppt", "").replaceAll(".xls",".pdf").replaceAll(".txt",".pdf").replaceAll(".docx",".pdf").replaceAll(".xlsx",".pdf").replaceAll(".pptx",".pdf");
			Office2pdfHelper oh = new Office2pdfHelper();
			System.loadLibrary("jacob-1.17-x86");
			oh.convert2PDF(fileTarget,filePdfTarget);*/
			//System.out.println("/*转换成功："+filePdfTarget+"*/");
			
			
			//System.out.println(fileSource);
			//System.out.println(fileTarget);
			int catalogId = 0;
			for (int n = 0 ;n<dps.length;n++){
				if (fileSource.contains(dps[n][1])){
					catalogId=Integer.parseInt(dps[n][0]);
				}
			}
			
			String sql_info_file = 
							"INSERT INTO info_file" +
							" (id, URL, name, type, catalog_id)" + 
							" VALUES" + 
							" (" + (i+1) + "," + 
							" '" + fileTarget + "'," + 
							" '" + sf.getName() + "'," + 
							" 1," + 
							" "+catalogId+");";
			
			System.out.println(sql_info_file);
			
			String sql_info_approve_log = 
							"INSERT INTO info_approve_log" +
							" (id," + 
							" name," + 
							" create_user," + 
							" create_time," + 
							" update_time," + 
							" status," + 
							" approve_file_id," + 
							" file_id)" + 
							"VALUES" + 
							" (" + (i+1) + "," + 
							" '" + sf.getName().substring(0,sf.getName().length()-4) + "'," + 
							" 'admin'," + 
							" '2015-07-01 00:00:00'," + 
							" '2015-07-01 00:00:00'," + 
							" 0," + 
							" NULL," + 
							" " + (i+1) + ");";

			System.out.println(sql_info_approve_log);
		}
		
		
	}
}
