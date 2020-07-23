package com.modules.common.generator.utils;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	DateUtil() {
		throw new AssertionError("工具类不能进行创建对象操作！");
	}

	public static final long HOUR = 60 * 60L * 1000L;

	public static final int HOUR_SECOND = 60 * 60;

	public static final int EIGHT_HOUR_SECOND = 60 * 60 * 8;// 8小时

	public static final int DAY_SECOND = 60 * 60 * 24;

	public static final long DAY_MILLI_SECOND = DAY_SECOND * 1000L;

	public static final long EIGHT_HOUR_MILLI_SECOND = EIGHT_HOUR_SECOND * 1000L;

	public final static String FORMAT_STRING = "yyyy-MM-dd HH:mm:ss";

	/**
	 * Date转字符串，格式为yyyy-MM-dd HH:mm:ss
	 *
	 * @param date 日期
	 * @return 字符串
	 */
	public static String date2String(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_STRING);
		return simpleDateFormat.format(date);
	}

	/**
	 * 获取日期
	 *
	 * @param date 日期
	 * @return 若date为空，返回当前日期
	 */
	public static Date defaultDate(Date date) {
		if (date == null) {
			return new Date();
		}
		return date;
	}

	/**
	 * 获取日期
	 *
	 * @param date        日期
	 * @param defaultDate 默认日期
	 * @return 若date为空，返回defaultDate转换后的日期
	 */
	public static Date defaultDate(Date date, long defaultDate) {
		if (date == null) {
			return new Date(defaultDate);
		}
		return date;
	}

	/**
	 * 获取日期
	 *
	 * @param date        日期
	 * @param defaultDate 默认日期
	 * @return 若date为空，返回defaultDate
	 */
	public static Date defaultDate(Date date, Date defaultDate) {
		if (date == null) {
			return defaultDate;
		}
		return date;
	}

	/**
	 * 根据formatString将Date转成字符串
	 *
	 * @param date         日期
	 * @param formatString 转换格式
	 * @return 字符串
	 */
	public static String date2String(Date date, String formatString) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatString);
		return simpleDateFormat.format(date);
	}

	/**
	 * 字符串日期转换成Date
	 *
	 * @param dateString
	 * @return
	 */
	public static Date str2Date(String dateString) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_STRING);
			return simpleDateFormat.parse(dateString);
		} catch (ParseException e) {
			throw new RuntimeException(
					"时间转化格式错误!" + "[dateString=" + dateString + "]" + "[FORMAT_STRING=" + FORMAT_STRING + "]");
		}
	}

	/**
	 * 字符串转成Date
	 *
	 * @param dateString  字符串日期
	 * @param defaultDate 默认日期
	 * @return 若出现异常返回默认日期
	 */
	public static Date str2Date(String dateString, Date defaultDate) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_STRING);
			return simpleDateFormat.parse(dateString);
		} catch (Exception e) {
			return defaultDate;
		}

	}

	/**
	 * 字符串日期转Date
	 *
	 * @param dateString  字符串日期
	 * @param formatDate  转换格式
	 * @param defaultDate 默认日起
	 * @return 若发生异常返回默认日期
	 */
	public static Date str2Date(String dateString, String formatDate, Date defaultDate) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatDate);
			return simpleDateFormat.parse(dateString);
		} catch (Exception e) {
			return defaultDate;
		}

	}

	/**
	 * 当前时间增加指定的分钟数
	 *
	 * @param minute 分钟数
	 * @return 增加分钟数后的Date
	 */
	public static Date addTime(final int minute) {
		long millis = System.currentTimeMillis();
		millis = millis + (minute * 60L * 1000L);
		Date date = new Date(millis);
		return date;
	}

	public static Date addSecond(final int seconds) {
		long millis = System.currentTimeMillis();
		millis = millis + (seconds * 1000L);
		Date date = new Date(millis);
		return date;
	}

	/**
	 * 将Date的HH-mm-ss置为零
	 *
	 * @param date 日期Date
	 * @return 转换后的Date
	 */
	public static Date getOnlyDate(Date date) {
		String dateStr = DateUtil.getDate(date);
		return DateUtil.toDate(dateStr + " 00:00:00");
	}

	/**
	 * 日期Date增加指定天数
	 *
	 * @param date   日期Date
	 * @param daynum 天数
	 * @return 增加天数后的Date
	 */
	public static Date addDate(final Date date, final int daynum) {
		int minute = daynum * 60 * 24;
		return addTime(date, minute);
	}

	/**
	 * 获取今天的日期
	 *
	 * @return
	 */
	public static Date getToday() {
		return getOnlyDate(new Date());
	}

	/**
	 * 获取明天的日期
	 *
	 * @return
	 */
	public static Date getTomorrow() {
		return getOnlyDate(DateUtil.addDate(new Date(), 1));
	}

	/**
	 * 获取今天的日期
	 *
	 * @return
	 */
	public static Date getTodayLastTime() {
		Date today = getOnlyDate(new Date());
		return new Date(today.getTime() + DateTime.DAY_MILLIS - 1000L);
	}

	/**
	 * 获取当前最后的时间
	 *
	 * @return
	 */
	public static Date getLastTime(Date dayFirstTime) {
		return new Date(dayFirstTime.getTime() + DateTime.DAY_MILLIS - 1000L);
	}

	/**
	 * 日期Date增加指定分钟数
	 *
	 * @param startDate 日期Date
	 * @param minute    分钟数
	 * @return 增加分钟数后的Date
	 */
	public static Date addTime(final Date startDate, final int minute) {
		long millis = startDate.getTime();
		millis = millis + (minute * 60L * 1000L);
		Date date = new Date(millis);
		return date;
	}

	/**
	 * 返回当前时间值
	 *
	 * @return
	 */
	public static Date getTime() {
		return new Date();
	}

	/**
	 * 根据Date获取字符串日期yyyy-MM-dd
	 *
	 * @param date 日期Date
	 * @return 字符串日期
	 */
	public static String getDate(Date date) {
		String time = DateUtil.getTime(date);
		if (time == null) {
			return null;
		}
		return time.substring(0, 10);
	}

	/**
	 * 根据Date获取字符串日期yyyy-MM-dd HH:mm:ss
	 *
	 * @param date 日期Date
	 * @return 字符串日期
	 */
	public static String getTime(Date date) {
		if (date == null) {
			return null;
		}
		long millis = date.getTime();
		return DateTime.getTime(millis);
	}

	/**
	 * 根据指定格式从Date获取字符串日期
	 *
	 * @param date   日期Date
	 * @param format 转换格式
	 * @return 字符串日期
	 */
	public static String getTime(Date date, String format) {
		// try {
		DateFormat df = new SimpleDateFormat(format);
		return df.format(date);
		// }
		// catch (IllegalArgumentException e) {
		// throw new RuntimeException(e.getMessage(), e);
		// }
	}

	/**
	 * 时间值（毫秒数）转Date
	 *
	 * @param time 毫秒数
	 * @return 日期Date
	 */
	public static Date toDate(Long time) {
		if (time == null || time <= 0) {
			return null;
		}
		return new Date(time);
	}

	/**
	 * 根据Date获取时间戳
	 *
	 * @param date 日期Date
	 * @return 时间值，以毫秒为单位
	 */
	public static long getTimestamp(final Date date) {
		return date.getTime();
	}

	/**
	 * 字符串日期转Date
	 *
	 * @param datetime 字符串日期
	 * @return 日期Date
	 */
	public static Date toDate(String datetime) {
		if (datetime == null || datetime.length() == 0) {
			return null;
		}
		long time = DateTime.getTimestamp(datetime);
		if (time <= 0) {
			throw new IllegalArgumentException("非法日期[" + datetime + "]");
		}
		return new Date(time);
	}

	/**
	 * 获取秒数(从1970-01-01 00:00:00开始)
	 *
	 * @return 秒数
	 */
	public static int getSeconds() {
		int seconds = (int) (System.currentTimeMillis() / 1000L);
		return seconds;
	}

	/**
	 * 获取秒数(从2010-01-01 00:00:00开始)
	 *
	 * @return 秒数
	 */
	public static int getShortSeconds() {
		int seconds = getSeconds();
		// 1262275200 = 2010-01-01 00:00:00
		return seconds - 1262275200;
	}

	/**
	 * 获取秒数(从2010-01-01 00:00:00开始)
	 *
	 * @return 秒数
	 */
	public static int getShortSeconds(Date date) {
		// int se = 1334567890;
		int seconds = (int) (date.getTime() / 1000L);
		// 1262275200 = 2010-01-01 00:00:00
		return seconds - 1262275200;
	}

	/**
	 * 返回时间值(2010-01-01 00:00:00)增加指定秒数后的日期Date
	 *
	 * @param time 秒数
	 * @return 增加秒数后的Date
	 */
	public static Date toLongDate(Double time) {
		if (time == null) {
			return null;
		}
		return toLongDate(time.intValue());
	}

	/**
	 * 返回时间值(2010-01-01 00:00:00)增加指定秒数后的日期Date
	 *
	 * @param time 秒数
	 * @return 增加秒数后的Date
	 */
	public static Date toLongDate(int time) {
		if (time <= 0) {
			return null;
		}
		int seconds = time + 1262275200;
		return new Date(seconds * 1000L);
	}

	/**
	 * 根据Date获取秒数
	 *
	 * @param date 日期Date
	 * @return 秒数
	 */
	public static int getSecond(final Date date) {
		long time = date.getTime();
		return (int) (time / 1000);
	}

	/**
	 * 判断传入的日期是否为今天
	 *
	 * @return boolean
	 */
	public static boolean isToday(Date date) {
		if (date == null) {
			return false;
		}
		String time = getTime(date);
		return DateTime.isToday(time);
	}

	/**
	 * 获取当前时间x秒前/后的时间
	 *
	 * @param second （-x：前 x：后）
	 * @return
	 */
	public static Date getBeforeSecond(Date date, int second) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.SECOND, second);
		return cal.getTime();
	}

	/**
	 * 获取月份的第一天
	 *
	 * @param monthNum 0:表示当前月份 负数：表示前n个月份 整数：表示后n个月份
	 * @return 指定月份第一天的日期字符串
	 */
	public static Date getFirstDayOfMonth(final int monthNum) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, monthNum);
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return new Date(cal.getTimeInMillis());
	}

	/**
	 * 获取月份的第一天
	 *
	 * @param monthNum 0:表示当前月份 负数：表示前n个月份 整数：表示后n个月份
	 * @return 指定月份第一天的日期字符串
	 */
	public static Date getFirstDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return new Date(cal.getTimeInMillis());
	}

	/**
	 * 字符串日期根据指定格式转换成Date
	 *
	 * @param dateString   字符串日期
	 * @param formatString 转换格式
	 * @return 转换后的Date
	 */
	public static Date str2Date(String dateString, String formatString) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatString);
			return simpleDateFormat.parse(dateString);
		} catch (ParseException e) {
			throw new RuntimeException(
					"时间转化格式错误!" + "[dateString=" + dateString + "]" + "[FORMAT_STRING=" + formatString + "]");
		}
	}

	// /**
	// * 字符串日期根据指定格式转换成Date
	// *
	// * @param dateString
	// * 字符串日期
	// * @param defaultDate
	// * 默认日期
	// * @param formatString
	// * 转换格式
	// * @return 转换后的Date，若发送异常返回默认日期
	// */
	// public static Date str2Date(String dateString, Date defaultDate, String
	// formatString) {
	// try {
	// SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatString);
	// return simpleDateFormat.parse(dateString);
	// }
	// catch (Exception e) {
	// return defaultDate;
	// }
	//
	// }

	/**
	 * 判断date1是否早于date2
	 *
	 * @param date1 日期1
	 * @param date2 日期2
	 * @return boolean
	 */
	public static boolean before(Date date1, Date date2) {
		if (date1 == null && date2 == null) {
			return false;
		}
		if (date1 == null && date2 != null) {
			return false;
		}
		if (date1 != null && date2 == null) {
			return true;
		}
		return date1.before(date2);
	}

	/**
	 * 判断两个时间值是否为同一时间
	 *
	 * @param second1 时间1
	 * @param second2 时间2
	 * @return boolean
	 */
	public static boolean isEqualsDay(int second1, int second2) {
		int diffDayCount = getDiffDayCount(second1, second2);
		return diffDayCount == 0;
	}

	// public static int getMonth(Date date) {
	// Calendar cld = Calendar.getInstance();
	// cld.setTime(date);
	// return cld.get(Calendar.MONTH);
	// }
	//
	// public static int getDay(Date date) {
	// Calendar cld = Calendar.getInstance();
	// cld.setTime(date);
	// return cld.get(Calendar.DAY_OF_MONTH);
	// }

	// public static int getMonth(int second) {
	// int hour = (second + EIGHT_HOUR_SECOND) % DAY_SECOND / HOUR_SECOND;
	// return hour;
	// }
	//
	// public static int getDay(int second) {
	// int hour = (second + EIGHT_HOUR_SECOND) % DAY_SECOND / HOUR_SECOND;
	// return hour;
	// }

	/**
	 * 根据秒数获取小时
	 *
	 * @param second 描述
	 * @return 小时
	 */
	public static int getHour(int second) {
		int hour = (second + EIGHT_HOUR_SECOND) % DAY_SECOND / HOUR_SECOND;
		return hour;
	}

	/**
	 * 获取两个时间值(以秒为单位)相差的天数
	 *
	 * @param postSecond    时间1
	 * @param currentSecond 时间2
	 * @return 相差的天数
	 */
	public static int getDiffDayCount(int postSecond, int currentSecond) {
		int posttimeDay = (postSecond + EIGHT_HOUR_SECOND) / DAY_SECOND;
		int currentDay = (currentSecond + EIGHT_HOUR_SECOND) / DAY_SECOND;
		// System.out.println("currentDay:" + currentDay + " posttimeDay:" +
		// posttimeDay);
		return currentDay - posttimeDay;
	}

	/**
	 * 计算二个时间相差的天数</br>
	 *
	 * @param date1 日期
	 * @param date2 日期
	 * @return 相差的天数
	 */
	public static int getDays(String date1, String date2) {
		// 转换为标准时间
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		Date mydate = null;
		try {
			date = myFormatter.parse(date1);
			mydate = myFormatter.parse(date2);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		return (int) day;
	}

	public static int getDayCount(Date date1, Date date2) {
		int dayCount1 = DateTime.getDayCount(date1);
		int dayCount2 = DateTime.getDayCount(date2);
		return (dayCount1 - dayCount2);
	}

	// public static int getDays(Date date1, Date date2) {
	// long day = (date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000);
	// return (int) day;
	// }
	// public static void main(String[] args) {
	// // int s = 315532800;
	// // int seconds =
	// // DateUtil.getShortSeconds(DateUtil.toDate("2020-01-01 00:00:00"));
	// // System.out.println("seconds:" + seconds);
	// System.out.println(DateUtil.getSeconds());
	// // int hour = (DateUtil.getSeconds()) % DAY_SECOND / HOUR_SECOND;
	//
	// int time = DateUtil.getHour(DateUtil.getSeconds());
	// System.out.println("time:" + time);
	// }
}
