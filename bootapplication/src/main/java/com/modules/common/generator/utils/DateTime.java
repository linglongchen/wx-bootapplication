package com.modules.common.generator.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期、时间常用方法类.
 * <p>
 *
 * @author caizx
 *
 */
public class DateTime {
	DateTime() {
		throw new AssertionError("工具类不能进行创建对象操作！");
	}

	public static final long HOUR_MILLIS = 1000 * 60 * 60;
	public static final long DAY_MILLIS = HOUR_MILLIS * 24;
	public static final int EIGHT_HOUR_SECOND = 60 * 60 * 8;// 8小时
	public static final int DAY_SECOND = 60 * 60 * 24;// 24小时
	public static final long EIGHT_HOUR_MILLI_SECOND = EIGHT_HOUR_SECOND * 1000L;

	/**
	 * 获取从1970年1月1日经历的天数</br>
	 *
	 * @return 天数
	 */
	public static int getDayCount() {
		long daynum = (System.currentTimeMillis() + EIGHT_HOUR_MILLI_SECOND) / DAY_MILLIS;
		return (int) daynum;
	}

	/**
	 * 获取小时数量.</br>
	 *
	 * @return 小时数
	 */
	public static int getHourCount() {
		long daynum = System.currentTimeMillis() / HOUR_MILLIS;
		return (int) daynum;
	}

	/**
	 * 获取天数
	 *
	 * @param date 日期
	 * @return 天数
	 */
	public static int getDayCount(final Date date) {
		return getDayCount(date.getTime());
	}

	/**
	 * 获取天数
	 *
	 * @param date 日期
	 * @return 天数
	 */
	public static int getDayCount(final long time) {
		long daynum = (time + EIGHT_HOUR_MILLI_SECOND) / DAY_MILLIS;
		return (int) daynum;
	}

	/**
	 * 获取天数
	 *
	 * @param datetime 日期
	 * @return 天数
	 */
	public static int getDayCount(final String datetime) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date;
		try {
			date = format.parse(datetime);
		} catch (ParseException e) {
			throw new IllegalArgumentException(e.getMessage(), e);
		}
		long daynum = (date.getTime() + EIGHT_HOUR_MILLI_SECOND) / DAY_MILLIS;
		return (int) daynum;

	}

	/**
	 * 获取两个日期之间相差的天数
	 *
	 * @param date1 开始日期
	 * @param date2 结束日期
	 * @return 天数
	 */
	public static int getDayCount(final String date1, String date2) {

		int dayCount1 = DateTime.getDayCount(date1);
		int dayCount2 = DateTime.getDayCount(date2);
		return (dayCount1 - dayCount2);
	}

	/**
	 * 获取当天日期，格式为yyyy-MM-dd
	 *
	 * @return 当天日期字符串
	 */
	public static String getDate() {
		return getDate(System.currentTimeMillis());
	}

	/**
	 * 获取指定天数后的日期，格式为yyyy-MM-dd
	 *
	 * @param daynum 天数
	 * @return 日期字符串
	 */
	public static String getDate(final int daynum) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, daynum);
		return getDate(cal.getTimeInMillis());
	}

	/**
	 * 获取指定天数后的日期，格式为yyyy-MM-dd
	 *
	 * @param daynum 天数
	 * @return 日期字符串
	 */
	public static String addDate(final int daynum) {
		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.DATE, daynum);

		return getDate(cal.getTimeInMillis());
	}

	/**
	 * 返回给定日期之后指定天数后的日期，格式为yyyy-MM-dd
	 *
	 * @param date   日期
	 * @param daynum 天数
	 * @return 日期字符串
	 */
	public static String addDate(final String date, final int daynum) {
		int[] arr = parseDatetimeToArray(date);
		int year = arr[0];
		int month = arr[1];
		int day = arr[2];

		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day + daynum);
		return getDate(cal.getTimeInMillis());
	}

	/**
	 * 将时间或者日期转换成int数组
	 *
	 * @param datetime 时间
	 * @return int数组
	 */
	public static int[] parseDatetimeToArray(final String datetime) {
		int year = Integer.parseInt(datetime.substring(0, 4));
		int month = Integer.parseInt(datetime.substring(5, 7));
		int day = Integer.parseInt(datetime.substring(8, 10));

		return new int[] { year, month, day };
	}

	private static final SimpleDateFormat GET_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 毫秒数转换成日期，格式为yyyy-MM-dd
	 *
	 * @param millis 毫秒数
	 * @return 日期字符串
	 */
	public static synchronized String getDate(final long millis) {
		Date date = new Date();
		if (millis > 0) {
			date.setTime(millis);
		}
		return GET_DATE_FORMAT.format(date);
	}

	/**
	 * 获取当前小时
	 *
	 * @return 小时
	 */
	public static int getHour() {
		Calendar cld = Calendar.getInstance();
		return cld.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 返回当天为月中的某一天
	 *
	 * @return 天
	 */
	public static int getDay() {
		Calendar cld = Calendar.getInstance();
		return cld.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取当前月份
	 *
	 * @return 月份
	 */
	public static int getMonth() {
		Calendar cld = Calendar.getInstance();
		return cld.get(Calendar.MONTH);
	}

	/**
	 * 获取当前分钟
	 *
	 * @return 分钟
	 */
	public static int getMinute() {
		Calendar cld = Calendar.getInstance();
		return cld.get(Calendar.MINUTE);
	}

	/**
	 * 获取当前毫秒数，格式为yyyy-MM-dd HH:mm:ss
	 *
	 * @return 毫秒
	 */
	public static String getTime() {
		return getTime(0);
	}

	/**
	 * 获取当前时候后指定分钟数的毫秒数 long millis加上int minute 如int很大会出现被载断情况，得出的结果错误，
	 * 现将int转换成long后再进行计算. 如计算一年后的时间24*60*365*60*1000此数已超出int范围,截断后计算出错。
	 *
	 * @param minute 指定的分钟数
	 * @return 毫秒数
	 */
	public static String addTime(final int minute) {
		long millis = System.currentTimeMillis();
		millis = millis + (minute * 60L * 1000L);
		return getTime(millis);
	}

	/**
	 * 获取给定时间后指定分钟数的毫秒数，格式为yyyy-MM-dd HH:mm:ss
	 *
	 * @param time   给定的时间
	 * @param minute 指定的分钟数
	 * @return 毫秒数
	 */
	public static String addTime(String time, int minute) {
		long millis = getTimestamp(time);
		millis = millis + (minute * 60L * 1000L);
		return getTime(millis);
	}

	private static final SimpleDateFormat GET_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 根据秒数获取毫秒数，格式为yyyy-MM-dd HH:mm:ss
	 *
	 * @param second 秒数
	 * @return 毫秒数
	 */
	public static String getTime(int second) {
		long millis = second * 1000L;
		return getTime(millis);
	}

	/**
	 * 根据日期返回毫秒数，格式为yyyy-MM-dd HH:mm:ss
	 *
	 * @param date 日期
	 * @return 毫秒数
	 */
	public static String getTime(Date date) {
		long millis = date.getTime();
		return getTime(millis);
	}

	/**
	 * 根据字符串类型日期返回毫秒数
	 *
	 * @param time
	 * @return
	 */
	public static String getTime(String time) {
		if (time == null) {
			return null;
		} else {
			return time.substring(0, 19);
		}
	}

	/**
	 * long型毫秒数转成yyyy-MM-dd HH:mm:ss类型的字符串型日期
	 *
	 * @param millis 毫秒数
	 * @return 日期
	 */
	public static synchronized String getTime(final long millis) {
		Date date = new Date();
		if (millis != 0) {
			date.setTime(millis);
		}
		return GET_TIME_FORMAT.format(date);
	}

	/**
	 * 根据字符串获取时间戳
	 *
	 *
	 * @param datetime 字符串时间
	 * @return
	 */
	public static long getTimestamp(final String datetime) {
		// 2009-10-10 01:01:01.1
		// if (!DateTime.isDateTime(datetime)) {
		// return 1;
		// }
		Calendar cal = Calendar.getInstance();

		int year = Integer.parseInt(datetime.substring(0, 4));
		int month = Integer.parseInt(datetime.substring(5, 7));
		int day = Integer.parseInt(datetime.substring(8, 10));

		int hour = Integer.parseInt(datetime.substring(11, 13));
		int minute = Integer.parseInt(datetime.substring(14, 16));
		int second = Integer.parseInt(datetime.substring(17, 19));

		// System.out.println(year + ":" + month + ":" + day);
		// System.out.println(hour + ":" + minute + ":" + second);
		cal.set(year, month - 1, day, hour, minute, second);
		if (datetime.length() > 19) {
			int mill = Integer.parseInt(datetime.substring(20));
			cal.set(Calendar.MILLISECOND, mill);
		} else {
			cal.set(Calendar.MILLISECOND, 0);
		}

		return cal.getTimeInMillis();
	}

	/**
	 * 获取当前时间戳
	 *
	 * @return 当前时间，以毫秒为单位
	 */
	public static long getTimestamp() {
		Calendar cal = Calendar.getInstance();
		return cal.getTimeInMillis();
	}

	/**
	 * 获取Unix下的时间戳
	 *
	 * @return 当前时间，以毫秒为单位
	 */
	public static int getUnixTimestamp() {
		long timestamp = getTimestamp();
		return (int) (timestamp / 1000);
	}

	/**
	 * 根据字符串时间获取Unix下的时间戳
	 *
	 * @param datetime 字符串日期
	 * @return 时间值，以毫秒为单位
	 */
	public static int getUnixTimestamp(String datetime) {
		long timestamp = getTimestamp(datetime);
		// System.out.println("timestamp:" + timestamp);
		return (int) (timestamp / 1000);
	}

	private static final String IS_DATE_REGEX = "^[0-9]{4}\\-[0-9]{1,2}\\-[0-9]{1,2}$";

	/**
	 * 判断字符串是否为正确的日期格式
	 *
	 * @param str 字符串日期
	 * @return 是否合法日期格式
	 */
	public static boolean isDate(final String date) {
		if (date == null) {
			return false;
		}

		return date.matches(IS_DATE_REGEX);
	}

	private static final String IS_TIME_REGEX = "^[0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}$";

	/**
	 * 判断字符串是否为正确的时间格式
	 *
	 * @param time 格式:10:10:10
	 * @return 是否合法时间格式
	 */
	public static boolean isTime(final String time) {
		if (time == null) {
			return false;
		}
		return time.matches(IS_TIME_REGEX);
	}

	private static final String IS_DATETIME_REGEX = "^[0-9]{4}\\-[0-9]{1,2}\\-[0-9]{1,2} [0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}(\\.[0-9]{1,3})?$";

	/**
	 * 判断字符串是否为正确的日期 + 时间格式
	 *
	 * @param datetime 格式:2010-10-10 00:00:00
	 * @return 是否合法日期 + 时间格式
	 */
	public static boolean isDateTime(final String datetime) {
		if (datetime == null || datetime.length() == 0) {
			return false;
		}
		return datetime.matches(IS_DATETIME_REGEX);
	}

	/**
	 * 根据字符串时间获取秒数
	 *
	 * @param datetime 字符串时间
	 * @return 秒数
	 */
	public static int getSecond(final String datetime) {
		long time = getTimestamp(datetime);
		return (int) (time / 1000);
	}

	/**
	 * 根据字符串时间获取GMT时间
	 *
	 * @param time
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getGMT(final String time) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(getTimestamp(time));
		Date date = cal.getTime();
		return date.toGMTString();
	}

	private static final String[] CN_WEEK_NAMES = { "天", "一", "二", "三", "四", "五", "六" };

	/**
	 * 返回当前日期是星期几，中文版
	 *
	 * @return 星期几
	 */
	public static String getWeekName() {
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_WEEK) - 1;
		return CN_WEEK_NAMES[day];
	}

	private static final String[] EN_WEEK_NAMES = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };

	/**
	 * 根据字符串时间返回星期几
	 *
	 * @param datetime 字符串时间
	 * @return 星期几
	 */
	public static String getWeekName(final String datetime) {
		Calendar cld = Calendar.getInstance();
		cld.setTimeInMillis(getTimestamp(datetime));
		int num = cld.get(Calendar.DAY_OF_WEEK) - 1;
		return EN_WEEK_NAMES[num];
	}

	/**
	 * 获取月份的天数
	 *
	 * @param monthNum 0:表示当前月份 负数：表示前n个月份 整数：表示后n个月份
	 * @return 天数
	 */
	public static int getDayCountOfMonth(final int monthNum) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, monthNum);
		cal.set(Calendar.DATE, 1);
		int daynum = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		return daynum;
	}

	/**
	 * 获取月份的第一天，格式为yyyy-MM-dd
	 *
	 * @param monthNum 0:表示当前月份 负数：表示前n个月份 整数：表示后n个月份
	 * @return 指定月份第一天的日期字符串
	 */
	public static String getFirstDayOfMonth(final int monthNum) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, monthNum);
		cal.set(Calendar.DATE, 1);
		return getDate(cal.getTimeInMillis());
	}

	/**
	 * 根据给定的日期，返回指定monthNum个月后的月份的第一天，格式为yyyy-MM-dd
	 *
	 * @param date     日期
	 * @param monthNum 月数
	 * @return date指定monthNum个月后的月份第一天的日期字符串
	 */
	public static String getFirstDayOfMonth(final String date, final int monthNum) {
		int[] arr = parseDatetimeToArray(date);
		int year = arr[0];
		int month = arr[1];
		int day = arr[2];

		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);
		cal.add(Calendar.MONTH, monthNum);
		cal.set(Calendar.DATE, 1);
		return getDate(cal.getTimeInMillis());
	}

	/**
	 * 根据给定的日期，返回指定monthNum个月后的月份的最后一天，格式为yyyy-MM-dd
	 *
	 * @param date     日期
	 * @param monthNum 月数
	 * @return date指定monthNum个月后的月份第一天的日期字符串
	 */
	public static String getLastDayOfMonth(final String date, final int monthNum) {
		int[] arr = parseDatetimeToArray(date);
		int year = arr[0];
		int month = arr[1];
		int day = arr[2];

		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day);
		cal.add(Calendar.MONTH, monthNum);
		cal.set(Calendar.DATE, 1);
		cal.add(Calendar.DATE, -1);
		return getDate(cal.getTimeInMillis());
	}

	/**
	 * 获取星期一，格式为yyyy-MM-dd
	 *
	 * @param date
	 * @return 星期一的字符串日期
	 */
	public static String getMonday(final String date) {
		int[] arr = parseDatetimeToArray(date);
		int year = arr[0];
		int month = arr[1];
		int day = arr[2];

		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);

		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.set(Calendar.DAY_OF_WEEK, 2);
		return getDate(cal.getTimeInMillis());
	}

	/**
	 * 判断传入的日期是否为今天
	 *
	 * @param time 字符串日期
	 * @return boolean 若为今天则返回true
	 */
	public static boolean isToday(String time) {
		if (time == null || time.length() < 10) {
			return false;
		}

		time = time.substring(0, 10);
		if (DateTime.getDate().equals(time)) {
			return true;
		}
		return false;
	}

	private static final SimpleDateFormat GET_INT_TIME_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");

	/**
	 * 获取当前时间，格式为yyyyMMddHHmmss
	 *
	 * @return 当前时间
	 */
	public static synchronized String getIntTime() {
		Date date = new Date();
		return GET_INT_TIME_FORMAT.format(date);
	}

}
