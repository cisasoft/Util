package com.cisa.util.encrypt;

import java.util.Random;

/**
 * 操作随机数
 * 
 * @author Xiaolong.Cisa
 * @version 1.0
 */
public class RandomHelper {

	/**
	 * 获取指定位数随机数
	 * 
	 * @param i
	 *            指定的位数
	 * @return 得到的随机数字符串
	 */
	public static String getRandom(int i) {
		Random r = new Random();
		if (i == 0) {
			return "";
		} else {
			String j = "";
			for (int k = 0; k < i; k++) {
				j += r.nextInt(9);
			}
			return j;
		}
	}

//	public static void main(String[] args) {
//		for (int i = 0; i < 100; i++)
//			System.out.println(getRandom(30));
//	}

}
