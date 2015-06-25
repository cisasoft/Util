package com.cisa.util.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 压缩解压zip文件
 * @author Xiaolong.Cisa
 * @version 1.0
 */
public class ZipHelper {

	//C:\\MyFile.zip
	//C:\\spy.log
	//spy.log
	public static boolean zipFiles(String sorucePath,String targetPath,String zipName){
		try {
			byte[] buffer = new byte[1024];
			FileInputStream in = new FileInputStream(sorucePath);
			FileOutputStream fos = new FileOutputStream(targetPath);
			ZipOutputStream zos = new ZipOutputStream(fos);
			ZipEntry ze= new ZipEntry(zipName);
			zos.putNextEntry(ze);
			
			int len;
			while ((len = in.read(buffer)) > 0) {
				zos.write(buffer, 0, len);
			}

			zos.closeEntry();
			//remember close it
			zos.close();
			fos.close();
			in.close();
			System.out.println("压缩完成");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}
	
	public static boolean zipFolders(String soruceFolder,String OutputZipFile){
		List<String> fileList;

	    
		
		
		return true;
	}
	
}
