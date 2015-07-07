package com.cisa.test;

import com.cisa.util.encrypt.MD5Helper;
import com.cisa.util.encrypt.ShortUUIDHelper;

import java.io.File;
import java.util.List;

import com.cisa.util.file.FileHelper;
import com.cisa.util.file.Office2pdfHelper;


public class Test {
/*
	
	public static void getCreateTime(){  
        String filePath = "c:\\test\\w环境监测需求.docx";
        File f = new File(filePath);
        String strTime = null;  
        try {  
            Process p = Runtime.getRuntime().exec("cmd /C dir "           
                    + filePath  
                    + " /tc" );
            System.out.println("cmd /C dir "           
                    + filePath  
                    + " /tc");
            System.out.println(f.getName());
            InputStream is = p.getInputStream();   
            BufferedReader br = new BufferedReader(new InputStreamReader(is,"gbk"));             
            String line;  
            while((line = br.readLine()) != null){  
            	System.out.println(line);
                if(line.endsWith(f.getName())){  
                    strTime = line.substring(0,17);  
                    break;  
                }                             
             }
            is.close();
        } catch (IOException e) {  
            e.printStackTrace();  
        }         
        System.out.println("创建时间    " + strTime);     
        //输出：创建时间   2009-08-17  10:21  
	}
	
	*//** 
     * 读取修改时间的方法2 
     *//*  
    public static void getModifiedTime_2(){  
        File f = new File("c:\\test\\w环境监测需求.docx");              
        Calendar cal = Calendar.getInstance();  
        long time = f.lastModified();  
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");         
        cal.setTimeInMillis(time);    
        System.out.println("修改时间[2] " + formatter.format(cal.getTime()));     
        //输出：修改时间[2]    2009-08-17 10:32:38  
    }  
	
*/	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*getCreateTime();
		getModifiedTime_2();
	    */
		
		
		String[][] str = { { "WXBYR", "叶然" }, { "xlq", "许良奇" },
				{ "WXBZS", "钟山" }, { "WXBWL", "王露" }, { "WXBHXR", "黄晓睿" },
				{ "WXBXLY", "俆立轶" }, { "WXBXYM", "徐诣敏" }, { "WXBLLL", "陆霖霖" },
				{ "WXBSRJ", "苏蓉娟" }, { "WXBQLK", "邱立科" }, { "WXBHM", "洪梅" },
				{ "WXBZYL", "赵彦龙" }, { "WXBXQ", "夏倩" }, { "WXBYL", "叶柳" },
				{ "WXBYCH", "苑程浩" }, { "WXBZR", "朱瑞" }, { "WXBLT", "李婷" },
				{ "WXBZC", "张弛" }, { "WXBSXF", "宋雪峰" }, { "WXBMDF", "缪丹凤" },
				{ "WXBMJJ", "孟吉杰 " }, { "WXBSYH", "孙燕红" }, { "WXBZWQ", "张伟清" },
				{ "WXBYQ", "叶青" }, { "WXBYJ", "杨瑾" }, { "WXBWQ", "王强" },
				{ "WXBGY", "顾晔" }, { "WXBYingJ", "应健" }, { "WXBQJH", "戚江虹" },
				{ "WXBLXL", "乐晓磊" }, { "WXBLH", "李皓" }, { "WXBFW", "冯卫" },
				{ "WXBZYZ", "张英姿" }, { "WXBLXY", "李晓宇" }, { "WXBDGR", "笪珪如" },
				{ "WXBYMQ", "姚明绮" }, { "WXBLYJ", "李颖佳" }, { "WXBQXF", "瞿晓风" },
				{ "WXBSXY", "沈晓颖" }, { "WXBLTY", "李婷玉" }, { "WXBHZQ", "何中晴" },
				{ "WXBYQQ", "杨琪" }, { "WXBCXY", "陈潇雨" }, { "WXBWJL", "王金龙" },
				{ "WXBJYZ", "姜益中" }, { "WXBHHX", "黄华星" }, { "WXBLDF", "李丹枫" },
				{ "WXBLJ", "刘杰" }, { "WXBLYQ", "李颜岐" }, { "WXBJQF", "姜青富" },
				{ "WXBSM", "施敏" }, { "WXBLIJI", "李季" } };

		String pwd = MD5Helper.getMD5("123456");
		
		for(int i = 0 ;i<str.length;i++){
			String uuid = ShortUUIDHelper.getId();
			String sql = "INSERT INTO sys_user VALUES ('"+uuid+"','"+str[i][0]+"','"+pwd+"','"+str[i][1]+"','2015-07-02','2015-07-02',0,0);";
			System.out.println(sql);
			sql = "INSERT INTO rel_group_user VALUES ('bPuMWkvk4XS98_11GMLXP7','"+uuid+"');";
			System.out.println(sql);
		}

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
