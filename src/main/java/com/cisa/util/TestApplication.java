package com.cisa.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.print.attribute.HashAttributeSet;

import com.cisa.util.encrypt.RandomHelper;
import com.cisa.util.file.FileHelper;

public class TestApplication {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Set<String> us = new HashSet<String>();
		List<String> l = FileHelper.getFileFromFolder("c:\\test2\\");
		String [][] strl = new String[l.size()][2];
		for (int i = 0; i < l.size(); i++){
			File f = new File(l.get(i));
			File fp = f.getParentFile();
			if(fp.isDirectory()){
				System.out.println(f.getAbsolutePath()+"-->"+fp.getName());
			}
			us.add(fp.getName());
			strl[i][0] = f.getAbsolutePath();
			strl[i][1] = fp.getName();
		}
		
		Iterator<String> it = us.iterator();  
		while (it.hasNext()){
			String is = it.next();
			System.out.println("需要创建的文件夹名字为 "+is);
			String path = "C:\\Apache\\apache-tomcat-8.0.21\\webapps\\wxb\\upload/"+is;
			File fm = new File(path);
			System.out.println("创建文件夹结果为 "+is+" "+fm.mkdirs());
		}
		
		for (int i = 0;i< strl.length;i++){
			File sf = new File(strl[i][0]);
			FileHelper.copyFile(strl[i][0], "C:\\Apache\\apache-tomcat-8.0.21\\webapps\\wxb\\upload/"+strl[i][1]+File.separator+RandomHelper.getRandom(18)+sf.getName());
		}
		
		
	}
}
