package com.cisa.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.cisa.util.http.RequestHelper;


public class TestApplication {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
//		RequestHelper.sendPost("https://selfsolve.apple.com/wcResults.do","sn=C02G8416DRJM&cn=&locale=&caller=&num=12345");
//		
//		RequestHelper.sendGet("http://www.google.com/search?q=mkyong");
		
//		RequestHelper.sendGet("http://ip.taobao.com/service/getIpInfo.php?ip=127.0.0.1");
		
//		sendGet1("http://192.168.2.152:8080/demo/ocr/scan?imagefile=IMAGE_TEST.JPG");
		
		sendGet1("http://api.famibao.cn/validationCodeServlet.png");
//		RequestHelper.sendPost("http://api.famibao.cn/employeeService/login.action","");
	}
	
	
	public static String sendGet1(String url) throws Exception {
		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("POST");

			// add request header
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			con.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.5");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);
			con.setAllowUserInteraction(false);

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream(), "UTF-8"));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
			return response.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR";
		}
	}
	
	
	
	
	
	
	
	
	
}
