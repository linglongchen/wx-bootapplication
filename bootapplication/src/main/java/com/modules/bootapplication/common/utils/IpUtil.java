package com.modules.bootapplication.common.utils;

import javax.servlet.http.HttpServletRequest;
import java.net.*;
import java.util.Enumeration;
import java.util.List;


public class IpUtil {
	/**
	 * 获取真实的ip地址
	 * @param request
	 * @return
	 * @author WangChengFei
	 * @date 2015-2-5
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
	    if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
	         //多次反向代理后会有多个ip值，第一个ip才是真实ip
	    	int index = ip.indexOf(",");
	        if(index != -1){
	            return ip.substring(0,index);
	        }else{
	            return ip;
	        }
	    }
	    ip = request.getHeader("X-Real-IP");
	    if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
	       return ip;
	    }
	    return request.getRemoteAddr();
	}
	/**
	 * 获取本机ip
	 * 通过 获取系统所有的networkInterface网络接口 然后遍历 每个网络下的InterfaceAddress组。
	 * 获得符合 <code>InetAddress instanceof Inet4Address</code> 条件的一个IpV4地址
	 * @return
	 * @author wcf
	 * @date 2015年12月9日
	 */
	@SuppressWarnings("rawtypes")
	public static String localIp(){
		String ip = null;
		Enumeration allNetInterfaces;
		try{
			allNetInterfaces = NetworkInterface.getNetworkInterfaces();
			while (allNetInterfaces.hasMoreElements()){
				NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
				List<InterfaceAddress> InterfaceAddress = netInterface.getInterfaceAddresses();
				for(InterfaceAddress add : InterfaceAddress){
					InetAddress Ip = add.getAddress();
					if (Ip != null && Ip instanceof Inet4Address) {
						ip = Ip.getHostAddress();
					}
				}
			}
		}catch(SocketException e){
			e.printStackTrace();
		}
		return ip;
	}
}
