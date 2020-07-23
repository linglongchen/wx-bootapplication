package com.modules.common.utils;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;

/**
 *
 * @author  huanglong
 * @version 1.0 2015年10月28日 下午4:39:08
 * 江苏中报
 */
public class PathUtil {

	private  static String WINDOWS="E:/";
	private  static String LINUX="/data/";


	/**
	 * 创建上传目录
	 * @param source
	 * @param softLink
	 */
	public static String initDirUpload(String source,String date){
		String realBaseDir =PathUtil.getFileSavePath(source);
		String path=realBaseDir+"/"+date;
		File baseFile = new File(path);
		if (!baseFile.exists()) {
			baseFile.mkdirs();
		}
		return path;
//		File softLinkFile = new File(softLink);
//		if (!softLinkFile.exists()) {
//			PathUtil.createSoftLink(softLink,realBaseDir);
//		}
	}


	/**
	 *
	 * @author  huanglong
	 * @version 1.0 2015-1-15 下午3:51:26
	 */
	public static void createSoftLink(String fromDir,String toDir){
		String osName = getSystemOs();
		String command="";
		if(osName.toLowerCase().contains("windows")){
			toDir=toDir.replaceAll("/", "\\\\");
			command="cmd /c mklink /D "+fromDir+" "+toDir;
		}else{
			command="ln -s  "+toDir +" "+fromDir;
		}
		try {
			Process p =Runtime.getRuntime().exec(command);
			if(p.waitFor()==0){
				System.out.println("create link ok");
			}else{
				System.out.println("create link error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 系统环境
	 * @author  huanglong
	 * @version 1.0 2015-1-15 下午3:51:34
	 */
	private static String getSystemOs() {
		return System.getProperty("os.name");
	}

	/**
	 * 相对路径
	 * @author  huanglong
	 * @version 1.0 2015-1-15 上午11:03:54
	 */
	public static String getContextFilePath(String realPathFile){
		String url="";
		String osName = getSystemOs();
		if(osName.toLowerCase().contains("windows")){
			url=StringUtils.remove(realPathFile, WINDOWS);
		}else{
			url=StringUtils.remove(realPathFile, LINUX);
		}
		return url;
	}

	/**
	 * 保存基本前缀
	 * @author  huanglong
	 * @version 1.0 2015-1-15 上午11:04:11
	 */
	public static String getFileSavePath(String filePath){
		String url="";
		String osName = getSystemOs();
		if(osName.toLowerCase().contains("windows")){
			url=WINDOWS+filePath;
		}else{
			url=LINUX+filePath;
		}
		return url;
	}

	/**
	 * 获得工程里的path，到 class 目录下面
	 * @return
	 */
	public static String getProjectPath(){
		ClassLoader cld = Thread.currentThread().getContextClassLoader();
		URL resource = cld.getResource("");
		String url = resource.getFile();
		url = URLDecoder.decode(url);
		return url;
	}

	//获取当前工程的web-inf路径
	public static String getWebInfPath(){
	     String path = getProjectPath();
	     if (path.indexOf("WEB-INF") > 0) {
	      path = path.substring(0, path.indexOf("WEB-INF")+8);
	     } else {
	      //throw new IllegalAccessException("路径获取错误");
	     }
	     return path;
	}




	//获取当前工程路径
	public static String getWebRoot(){
	     String path = getProjectPath();
	     if (path.indexOf("WEB-INF") > 0) {
	      path = path.substring(0, path.indexOf("WEB-INF/classes"));
	     } else {
	      //throw new IllegalAccessException("路径获取错误");
	     }
	     return path;
	}

	public static String getProjectPath(String path){
		return getProjectPath() + path;
	}


	public static void main(String[] args) {
//		System.out.println(getProjectPath());
//		createSoftLink(" D:\\apache-tomcat-7.0\\webapps\\DnionWorkflowII\\UserFiles");
	}
}
