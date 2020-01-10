package com.modules.bootapplication.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;

public class MathUtil {

	/**
	 * 通过总记录数和每页显示数，计算返回总页数
	 * @param total：总记录条数
	 * @param pageSize：每页显示的记录数
	 * @return
	 * @author wcf
	 * @date 2015年11月26日
	 */
	public static long getTotalPage(long total, int pageSize){
		return (long) Math.ceil((double)total / pageSize);
	}


	/**
	* 提供精确的小数位四舍五入处理。
	* @param v 需要四舍五入的数字
	* @param scale 小数点后保留几位
	* @return 四舍五入后的结果
	* @author wcf
	* @date 2015年11月30日
	*/
	public static double round(double v, int scale) {
		BigDecimal b = new BigDecimal(Double.toString(v));
		return round(b, scale);
	}

	/**
	* 提供精确的小数位四舍五入处理。
	* @param v 需要四舍五入的数字
	* @param scale 小数点后保留几位
	* @return 四舍五入后的结果
	* @author wcf
	* @date 2015年11月30日
	*/
	public static double round(BigDecimal v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
	    }
		BigDecimal ne = new BigDecimal("1");
		return v.divide(ne, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 四舍五入
	 * @param v
	 * @return
	 */
	public static BigDecimal round2(BigDecimal v) {
		BigDecimal ne = new BigDecimal("1");
		return v.divide(ne, 2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 将数字保留小数点后几位并返回字符串类型
	 * @param d
	 * @param scale
	 * @return
	 * @author wcf
	 * @date 2015年11月30日
	 */
	public static String roundAndStr(double d, int scale){
		return String .format("%." + scale + "f", d);
	}
	/**
	 * 将数字保留小数点后几位并返回字符串类型
	 * @param d
	 * @param scale
	 * @return
	 * @author wcf
	 * @date 2015年11月30日
	 */
	public static String roundAndStr(BigDecimal d, int scale){
		return String .format("%." + scale + "f", d.doubleValue());
	}

	/**
	 * 不等于空  大于0
	 * @param d
	 * @return
	 */
	public static boolean isNotNull(BigDecimal d){
		return d!=null&&d.compareTo(new BigDecimal(0))>0;
	}

	/**
	 * 7位唯一邀请码
	 * @param args
	 * @return
	 */
	public static String genInvitCode(long phone) {
		return Long.toString(phone, 36);
	}
	/**
	 * 经纪人邀请码生成规则
	 * @param
	 * @return
	 */
	public  static String agentcode(int j) {
		String str = new DecimalFormat("0000").format(j);
		return str;
	}
	/**
	 * 市场部生成规则
	 * @param
	 * @return
	 */
	public  static String marketcode() {
		String ran="";
		String str[]={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","p","q","r","s","t","u","v","w","x","y","z"};
		Random rn= new Random();
		for (int i = 0; i < 2; i++) {
			Integer rnint=rn.nextInt(str.length-1);
			ran +=str[rnint];
		}
		return ran;
	}
	/**
	 * @Description: 获取固定位数的随机整数
	 * @param length
	 * @return
	 * @author wcf
	 * @date 2016年10月31日
	 */
	public static String getValid(int length){
		if(length <= 0){
			return null;
		}
		Random rand = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append(rand.nextInt(10));
		}
		return sb.toString();
	}
}
