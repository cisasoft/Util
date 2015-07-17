package com.cisa.util.time;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 时间日期助手
 * 
 * @author Xiaolong.Cisa
 * @version 1.0
 */
public class DateHelper {

	/**
	 * 字符串转换为Date对象 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param strDate
	 *            字符串
	 * @return Date对象
	 */
	public static Date stringToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition ps = new ParsePosition(0);
		return formatter.parse(strDate, ps);
	}

	/**
	 * Date对象转换为字符串 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 *            Date对象
	 * @return 格式字符串
	 */
	public static String dateToString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(date);
	}

	/**
	 * 判断是否润年 整百年能被400整除的是闰年 非整百年能被4整除的为闰年
	 * 
	 * @param date
	 *            时间字符串"yyyy-MM-dd HH:mm:ss"
	 * @return 布尔值是或否
	 */
	public static boolean isLeapYear(String date) {
		Date d = stringToDate(date);
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(d);
		int year = gc.get(Calendar.YEAR);
		if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
			return true;
		} else
			return false;
	}
}