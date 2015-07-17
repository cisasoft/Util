package com.cisa.util.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * 发送请求的帮助类，模拟请求
 * 
 * @author Xiaolong.Cisa
 * 
 */
public class RequestHelper {
	//TODO

	private final static String USER_AGENT = "Mozilla/5.0";

	/**
	 * HTTP GET request
	 * 
	 * @param url
	 *            GET请求中的URL，例如： String url =
	 *            "http://www.google.com/search?q=mkyong"
	 * @throws Exception
	 *             向上级抛出异常
	 */
	public static String sendGet(String url) throws Exception {
		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");

			// add request header
			con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.5");

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

	/**
	 * HTTP POST request
	 * 
	 * @param url
	 *            请求的地址，String url = "https://selfsolve.apple.com/wcResults.do"
	 * @param urlParameters
	 *            附加的参数，以&分隔，例如：String urlParameters =
	 *            "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345"
	 * @throws Exception
	 */
	public static String sendPost(String url, String urlParameters) {
		try {
			URL obj = new URL(url);
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

			// add reuqest header
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.5");

			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Post parameters : " + urlParameters);
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
