package com.myutils.adutils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.myutils.str.OpUtils;
import com.myutils.str.StringUtils;

public class AdUtils {

	/**
	 * 记录日志,用\t分隔
	 */
	public static StringBuffer recordLog(StringBuffer log, String ... args) {
		if (log == null)
			log = new StringBuffer();
		for (String str : args) {
			log.append(str).append("\t");
		}
		return log;
	}
	
	/**
	 * 获取页数
	 * @param max 页数上限
	 * @param min 页数下限
	 * @param value
	 * @return 符合条件的页数
	 */
	public static int getPgnum(int max, int min, String value){
		if(StringUtils.checkEmpty(value) || !value.matches("[0-9]+")) return -100;
		int pgnum = Integer.parseInt(value);
		return (pgnum>max) ? max:((pgnum<min)?min:pgnum);
	}
	
	/**
	 * 获取渠道号,去掉末尾180316
	 * @param appqid xiaomi180316
	 * @return xiaomi
	 */
	public static String getAppqid(String appqid){
		appqid = OpUtils.check(appqid,"unknow");
		if(!StringUtils.checkEmpty(appqid) && appqid.length()>7){
			String data = appqid.substring(appqid.length()-6);
			Pattern pattern = Pattern.compile("([1-2][0-9])(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])");  
			Matcher m = pattern.matcher(data);
			if(m.find()){
				appqid = getAppqid(appqid.replace(data, ""));
			}
		}
		return appqid.toLowerCase();
	}
	
	/**
	 * 读取版本号-数字
	 * @param version 1.2.3
	 * @return versionNum 123
	 */
	public static int readVersion(String version){
		if(!StringUtils.checkEmpty(version))
			return Integer.parseInt(version.replaceAll("(\\D)|[.]", ""));
		throw new RuntimeException("version error : [ " +version+" ]");
	}
	
	
	

}
