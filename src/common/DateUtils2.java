package common;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

public class DateUtils2 {

	public static String formatter19 = "yyyy-MM-dd HH:mm:ss";

	public static String formatter10 = "yyyy-MM-dd";

	/**
	 * 获取当前时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getCurrentDate() {
		return new Date(System.currentTimeMillis());
	}

	/**
	 * 根据给定的格式把日期格式化为字符串
	 * 
	 * @author llf
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateToString(Date date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}

	/**
	 * 获取系统当前日期时间（Date类型）：格式yyyyMMddHHmmss
	 * 
	 * @return
	 */
	public static Date getSysTime() {
		// 获得日历类实例
		Calendar c = Calendar.getInstance();
		// 返回格式化后的结果
		return c.getTime();
	}

	public static String getDate19() {
		// 返回格式化后的结果
		SimpleDateFormat formatter = new SimpleDateFormat(formatter19);
		return formatter.format(new Date());
	}

	public static String getDate10() {
		SimpleDateFormat formatter = new SimpleDateFormat(formatter10);
		return formatter.format(new Date());
	}

}
