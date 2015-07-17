package com.cisa.util.encrypt;

import org.apache.commons.codec.binary.Base64;

/**
 * base64编码工具
 * 
 * @author Xiaolong.Cisa
 * @version 1.0
 */
public class Base64Helper {

	/**
	 * base64编码
	 * 
	 * @param plainText
	 *            输入的字符串
	 * @return 返回编码结果
	 */
	public static String encodeStr(String plainText) {
		byte[] b = plainText.getBytes();
		Base64 base64 = new Base64();
		b = base64.encode(b);
		String s = new String(b);
		return s;
	}

	/**
	 * base64解码
	 * 
	 * @param encodeStr
	 *            输入密文
	 * @return 解码的结果
	 */
	public static String decodeStr(String encodeStr) {
		byte[] b = encodeStr.getBytes();
		Base64 base64 = new Base64();
		b = base64.decode(b);
		String s = new String(b);
		return s;
	}
}
