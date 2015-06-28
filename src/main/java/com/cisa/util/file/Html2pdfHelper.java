package com.cisa.util.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class Html2pdfHelper {

	/**
	 * 
	 * @param htmlFile 原html路径
	 * @param pdfFile
	 */
	public static void getPdfWithItext(String htmlFile, String pdfFile) {
		try {
			// step 1
			Document document = new Document();
			// step 2
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
			// step 3
			document.open();
			//BaseFont baseFont = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
			//Font font = new Font(baseFont, 12, Font.NORMAL);
			//document.add(new Paragraph("汉字", font));
			// step 4
			XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(htmlFile),Charset.forName("UTF-8"));
			// step 5
			document.close();
			System.out.println("PDF Created!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("PDF Created Failure!");
		}
	}
	
	
	public static boolean wkhtmltopdf(String htmlPath, String pdfFile) {
		
		htmlPath = new File(htmlPath).getAbsoluteFile().toString();
		pdfFile = new File(pdfFile).getAbsoluteFile().toString();
		htmlPath = "http://mybatis.co.uk/";
		
		String localOS = System.getProperty("os.name");
		String command = null;
	    if(localOS.toLowerCase().contains("windows")){
	    	System.out.println("本机是Windows操作系统");
	    	command = "c://test//bin//wkhtmltopdf.exe" + " " + htmlPath + " " + pdfFile;
	    } else if(localOS.toLowerCase().contains("linux")){
	    	System.out.println("本机是Linux操作系统");
	    	command = "wkhtmltopdf-amd64" + " " + htmlPath + " " + pdfFile;
	    }
	    Process p = null;
	    try {System.out.println(command);
			 p= Runtime.getRuntime().exec(command);
			 
			 
			 //获取进程的标准输入流  
			  final InputStream is1 = p.getInputStream();   
			  //获取进城的错误流  
			  final InputStream is2 = p.getErrorStream();  
			  
			  final OutputStream is3 = p.getOutputStream();  
			  //启动两个线程，一个线程负责读标准输出流，另一个负责读标准错误流  
			  new Thread() {  
			     public void run() {  
			        BufferedReader br1 = new BufferedReader(new InputStreamReader(is1));  
			         try {  
			             String line1 = null;  
			             while ((line1 = br1.readLine()) != null) {  
			                   if (line1 != null){
			                   System.out.println("is1::   "+line1);
			                     }  
			               }  
			         } catch (IOException e) {  
			              e.printStackTrace();  
			         }  
			         finally{  
			              try {  
			                is1.close();  
			              } catch (IOException e) {  
			                 e.printStackTrace();  
			             }  
			           }  
			         }  
			      }.start();  
			                                 
			    new Thread() {   
			       public void  run() {   
			        BufferedReader br2 = new  BufferedReader(new  InputStreamReader(is2));   
			           try {   
			              String line2 = null ;   
			              while ((line2 = br2.readLine()) !=  null ) {   
			                   if (line2 != null){
			                   System.out.println("is2::   "+line2);
			                   }  
			              }   
			            } catch (IOException e) {   
			                  e.printStackTrace();  
			            }   
			           finally{  
			              try {  
			                  is2.close();  
			              } catch (IOException e) {  
			                  e.printStackTrace();  
			              }  
			            }  
			         }   
			       }.start();                  
			 
			 
			 
			 
			 p.waitFor();
			 System.out.println("PDF Created!");
			 return true;
//			boolean flag = false;
//			for(int i = 0;i<320;i++){
//				System.out.println("waiting for creating pdf...");
//				TimeUnit.SECONDS.sleep(2);
//				if (new File(pdfFile).exists()){
//					flag = true;
//					System.out.println("PDF Created!");
//					break;
//				}
//			}
//			return flag;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("PDF Created Failure!");
			return false;
		}finally{
			if (p != null){
				p.destroy();
			}
		}
	}
	
	
	public static void main(String[] args) throws Exception{
		//getPdfWithItext("c://test//index - 副本.html","c://test//htmlpdf.pdf");
		wkhtmltopdf("c://test//index.html","c://test//htmlpdf.pdf");
	    
	}
}
