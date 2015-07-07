package com.cisa.util.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class Html2pdfHelper {

	/**
	 * 使用原生的IText进行html转pdf，不建议使用本方法
	 * 因为本方法要求html必须是规范的XHTML，另外对于汉字等亚洲字体的解析必须在XHTML中使用style指定字体，否则IText不予识别
	 * 
	 * @param htmlFile
	 *            原html文件路径，不含网址解析
	 * @param pdfFile
	 *            指定的pdf输出路径
	 * @return 返回的布尔值是否成功
	 */
	public static boolean getPdfWithItext(String htmlFile, String pdfFile) {
		try {
			// step 1
			Document document = new Document();
			// step 2
			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream(pdfFile));
			// step 3
			document.open();
			// BaseFont baseFont =
			// BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
			// Font font = new Font(baseFont, 12, Font.NORMAL);
			// document.add(new Paragraph("汉字", font));
			// step 4
			XMLWorkerHelper.getInstance().parseXHtml(writer, document,
					new FileInputStream(htmlFile), Charset.forName("UTF-8"));
			// step 5
			writer.close();
			document.close();
			System.out.println("PDF Created!");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("PDF Created Failure!");
			return false;
		}
	}

	/**
	 * 利用工具wkhtmltopdf进行html转pdf
	 * 注意：使用前面先设置环境变量，把wkhtmltopdf安装路径的bin目录加入path中，本方法仅支持64位的调用
	 * 
	 * @param htmlPath
	 *            html文件的路径或是URL网址，例如 c://test//test.html或http://www.baidu.com/
	 * @param pdfFile
	 *            输出的pdf文件路径，例如 c://test//test.pdf
	 * @return 返回的布尔值是否成功
	 */
	public static boolean wkhtmltopdf(String htmlPath, String pdfFile) {

		File hf = new File(htmlPath);
		if (hf.exists())
			htmlPath = new File(htmlPath).getAbsoluteFile().toString();
		pdfFile = new File(pdfFile).getAbsoluteFile().toString();
		// htmlPath = "http://mybatis.co.uk/";

		// 获取操作系统版本Windows和linux分别不同处理
		String command = null;
		Process p = null;
		String localOS = System.getProperty("os.name");
		if (localOS.toLowerCase().contains("windows")) {
			System.out.println("本机是Windows操作系统");
			command = "wkhtmltopdf.exe" + " " + htmlPath + " " + pdfFile;
		} else if (localOS.toLowerCase().contains("linux")) {
			System.out.println("本机是Linux操作系统");
			command = "wkhtmltopdf-amd64" + " " + htmlPath + " " + pdfFile;
		}
		System.out.println("本机执行的操作系统命令是：\n" + command);

		try {
			p = Runtime.getRuntime().exec(command);

			// 获取进程的标准输入流
			final InputStream stdin = p.getInputStream();
			// 获取进程的标准错误流
			final InputStream stderr = p.getErrorStream();

			// 启动两个线程，一个线程负责读标准输入流，另一个负责读标准错误流
			new Thread() {
				public void run() {
					BufferedReader brin = new BufferedReader(
							new InputStreamReader(stdin));
					try {
						String line1 = null;
						while ((line1 = brin.readLine()) != null) {
							if (line1 != null) {
								System.out.println("stdin->" + line1);
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						try {
							stdin.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}.start();

			new Thread() {
				public void run() {
					BufferedReader brerr = new BufferedReader(
							new InputStreamReader(stderr));
					try {
						String line2 = null;
						while ((line2 = brerr.readLine()) != null) {
							if (line2 != null) {
								System.out.println("stderr->" + line2);
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						try {
							stderr.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}.start();

			p.waitFor();
			System.out.println("PDF Created!");
			return true;
			// boolean flag = false;
			// for(int i = 0;i<320;i++){
			// System.out.println("waiting for creating pdf...");
			// TimeUnit.SECONDS.sleep(2);
			// if (new File(pdfFile).exists()){
			// flag = true;
			// System.out.println("PDF Created!");
			// break;
			// }
			// }
			// return flag;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("PDF Created Failure!");
			return false;
		} finally {
			if (p != null) {
				p.destroy();
			}
		}
	}

	/**
	 * 利用工具wkhtmltoimg进行html转jpg
	 * 注意：使用前面先设置环境变量，把wkhtmltopdf安装路径的bin目录加入path中，本方法仅支持64位的调用
	 * 
	 * @param htmlPath
	 *            html文件的路径或是URL网址，例如 c://test//test.html或http://www.baidu.com/
	 * @param imgFile
	 *            输出的jpg文件路径，例如 c://test//test.jpg
	 * @return 返回的布尔值是否成功
	 */
	public static boolean wkhtmltoimg(String htmlPath, String imgFile) {

		File hf = new File(htmlPath);
		if (hf.exists())
			htmlPath = new File(htmlPath).getAbsoluteFile().toString();
		imgFile = new File(imgFile).getAbsoluteFile().toString();
		// htmlPath = "http://mybatis.co.uk/";

		// 获取操作系统版本Windows和linux分别不同处理
		String command = null;
		Process p = null;
		String localOS = System.getProperty("os.name");
		if (localOS.toLowerCase().contains("windows")) {
			System.out.println("本机是Windows操作系统");
			command = "wkhtmltoimage.exe" + " " + htmlPath + " " + imgFile;
		} else if (localOS.toLowerCase().contains("linux")) {
			System.out.println("本机是Linux操作系统");
			//command = "wkhtmltoimage-amd64" + " " + htmlPath + " " + imgFile;
			command = "wkhtmltoimage" + " " + htmlPath + " " + imgFile;
		}
		System.out.println("本机执行的操作系统命令是：\n" + command);

		try {
			p = Runtime.getRuntime().exec(command);

			// 获取进程的标准输入流
			final InputStream stdin = p.getInputStream();
			// 获取进程的标准错误流
			final InputStream stderr = p.getErrorStream();

			// 启动两个线程，一个线程负责读标准输入流，另一个负责读标准错误流
			new Thread() {
				public void run() {
					BufferedReader brin = new BufferedReader(
							new InputStreamReader(stdin));
					try {
						String line1 = null;
						while ((line1 = brin.readLine()) != null) {
							if (line1 != null) {
								System.out.println("stdin->" + line1);
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						try {
							stdin.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}.start();

			new Thread() {
				public void run() {
					BufferedReader brerr = new BufferedReader(
							new InputStreamReader(stderr));
					try {
						String line2 = null;
						while ((line2 = brerr.readLine()) != null) {
							if (line2 != null) {
								System.out.println("stderr->" + line2);
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						try {
							stderr.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}.start();

			p.waitFor();
			System.out.println("IMG Created!");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("IMG Created Failure!");
			return false;
		} finally {
			if (p != null) {
				p.destroy();
			}
		}
	}

	/*
	 * public static void main(String[] args) throws Exception {
	 * getPdfWithItext("c://test//维基百科自由的百科全书.html",
	 * "c://test//维基百科自由的百科全书itext.pdf");
	 * wkhtmltopdf("c://test//维基百科自由的百科全书.html", "c://test//维基百科自由的百科全书wk.pdf");
	 * wkhtmltoimg("http://www.baidu.com/", "c://test//baiduwk.jpg"); }
	 */
}
