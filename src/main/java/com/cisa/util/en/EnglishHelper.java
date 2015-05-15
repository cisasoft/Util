package com.cisa.util.en;

/**
 * 英文字符串处理函数
 * 
 * @author Daniel
 * @version 1.0
 * 
 */
public class EnglishHelper {

	/**
	 * 英语字符串取首字母
	 * 
	 * @param original
	 *            源字符串
	 * @return 返回处理后的字符串
	 */
	public static String getInitials(String original) {
		String initial = "";
		String[] split = original.split(" ");
		for (String value : split) {
			initial += value.substring(0, 1);
		}
		return initial;
	}
}
