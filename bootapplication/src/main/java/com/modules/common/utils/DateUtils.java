/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.modules.common.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * @author ThinkGem
 * @version 2014-4-15
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	private static String[] parsePatterns = {
		"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
		"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}

	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}

	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}

	/**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
	 *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
	 *   "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null){
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}
	/**
	 * 根据固定的格式，将字符串转化为Date
	 * @param str
	 * @param ftm
	 * @return
	 * @author wcf
	 * @date 2016年4月19日
	 */
	public static Date parseDate(String str, String ftm) {
		if (str == null){
			return null;
		}
		try {
			return new SimpleDateFormat(ftm).parse(str);
		} catch (ParseException e) {
			return null;
		}

	}

	/**
	 * 获取过去的天数
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(24*60*60*1000);
	}

	/**
	 * 获取过去的小时
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*60*1000);
	}

	/**
	 * 获取过去的分钟
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*1000);
	}

	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * @param timeMillis
	 * @return
	 */
    public static String formatDateTime(long timeMillis){
		long day = timeMillis/(24*60*60*1000);
		long hour = (timeMillis/(60*60*1000)-day*24);
		long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
		long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
		long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
		return (day>0?day+",":"")+hour+":"+min+":"+s+"."+sss;
    }

	/**
	 * 获取两个日期之间的天数，Date类型
	 *
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}
	/**
	 * 获取两个日期之间的天数，字符串格式
	 *
	 * @param before
	 * @param after
	 * @param fmt : 字符串的日期格式
	 * @return
	 */
	public static double getDistanceOfTwoDate(String before, String after, String fmt){
		Date beforeD = parseDate(before, fmt);
		Date afterD = parseDate(after, fmt);

		return getDistanceOfTwoDate(beforeD, afterD);
	}
	/**
	 * @Description: 获取两个日期之间的小时数
	 * @param before
	 * @param after
	 * @return
	 * @author wcf
	 * @date 2017年2月28日
	 */
	public static double getDistHoursOfTwoDate(Date before, Date after){
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60);
	}
	/**
	 * 获取本月的最后一天
	 * @return
	 * @author wcf
	 * @date 2016年3月21日
	 */
	public static String getLastDayOfThisMonth(){
		Calendar cal = Calendar.getInstance();
		//获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, lastDay);

        return formatDate(cal.getTime());
	}
	/**
	 * 获取本月的第一天
	 * @return
	 * @author wcf
	 * @date 2016年3月21日
	 */
	public static String getFirstDayOfThisMonth(){
		Calendar cal = Calendar.getInstance();
		//获取某月最大天数
        int lastDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, lastDay);

        return formatDate(cal.getTime());
	}
	/**
	 * 获取指定月份的最后一天
	 * @return
	 * @author wcf
	 * @date 2016年3月21日
	 */
	public static String getFirstDayOfMonth(int year, int month){
		Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);

        return formatDate(cal.getTime());
	}
	/**
	 * @Description: 当前日期是否在指定日期范围之内，begin <= now() <= end，时间为日期格式 yyyy-MM-dd
	 * @param begin
	 * @param end
	 * @return
	 * @author wcf
	 * @date 2016年11月22日
	 */
	public static boolean isBetweenTwoDate(Date begin, Date end){
		Date now = new Date();
		now = parseDate(formatDate(now, "yyyy-MM-dd"), "yyyy-MM-dd");

		return now.compareTo(begin) >= 0 && now.compareTo(end) <= 0;
	}
	/**
	 * @Description: 当前时间是否在指定时间之前，时间为日期格式 yyyy-MM-dd
	 * @param date
	 * @return
	 * @author wcf
	 * @date 2016年11月22日
	 */
	public static boolean isBefore(Date date){
		Date now = new Date();
		now = parseDate(formatDate(now, "yyyy-MM-dd"), "yyyy-MM-dd");

		return now.before(date);
	}
	/**
	 * @Description: 当前时间是否在指定时间之后，时间为日期格式 yyyy-MM-dd
	 * @param date
	 * @return
	 * @author wcf
	 * @date 2016年11月22日
	 */
	public static boolean isAfter(Date date){
		Date now = new Date();
		now = parseDate(formatDate(now, "yyyy-MM-dd"), "yyyy-MM-dd");

		return now.after(date);
	}
	/**
	 * @Description: 获取两个时间相差的分钟数
	 * @param start
	 * @param end
	 * @return
	 * @author wcf
	 * @date 2016年12月1日
	 */
	public static Integer diffMinute(Date start, Date end){
		return  (int)Math.ceil((double)(end.getTime() - start.getTime()) / (1000 * 60));
	}

	/**
	 * 通过毫秒时间戳获取小时数和分钟数
	 * @param time
	 * @return
	 */
	public static String getHourAndMinute(long time){
		int minute = (int)Math.ceil((double)(time) / (1000 * 60));
		int hours = (int) Math.floor((double)minute / 60);
		minute = minute % 60;
		StringBuilder sb = new StringBuilder();
		if (hours > 0){
			sb.append(hours).append("小时");
		}
		if (minute > 0){
			sb.append(minute).append("分");
		}
		return sb.toString();
	}

	/**
	 * @Description: 判断时间与当前时间是否为同一天
	 * @param date
	 * @return
	 * @author wcf
	 * @date 2017年1月23日
	 */
	public static boolean isSameDay(Date date){
		return diffMinute(parseDate(formatDate(date, "yyyy-MM-dd"), "yyyy-MM-dd"), parseDate(formatDate(new Date(), "yyyy-MM-dd"), "yyyy-MM-dd")) == 0;
	}

	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
//		System.out.println(formatDate(parseDate("2010/3/6")));
//		System.out.println(getDate("yyyy年MM月dd日 E"));
//		System.out.println(time/(24*60*60*1000));
//		System.out.println(diffMinute(parseDate("2016-12-01 17:00:00","yyyy-MM-dd HH:mm:ss"), new Date()));
//		System.out.println(Math.ceil(DateUtils.getDistHoursOfTwoDate(new Date(), DateUtils.parseDate("2017-03-01 10:00:00", "yyyy-MM-dd HH:mm:ss"))));
		System.out.println(getHourAndMinute(86770000));
	}
}
