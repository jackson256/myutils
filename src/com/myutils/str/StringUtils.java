package com.myutils.str;

public class StringUtils {

	public static <V> boolean isNull(V v) {
		return v == null || v == "" || v == "null" || "".equals(v) || "null".equals(v) ? true : false;
	}
	
	public static boolean checkEmpty(String str){
		return str == null || str == "" || "null".equals(str) || str.length() <= 0 ? true:false;
	}
	
	
	

}
