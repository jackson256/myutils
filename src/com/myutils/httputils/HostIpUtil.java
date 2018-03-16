package com.myutils.httputils;

import javax.servlet.http.HttpServletRequest;

public class HostIpUtil {
	
	/**
	 * 获取用户IP地址
	 * @param request
	 * @return
	 */
	public static String getRemoteIp(HttpServletRequest request){
		String remoteIp = request.getHeader("x-forwarded-for");
		if (remoteIp == null || remoteIp.length() == 0
				|| remoteIp.equalsIgnoreCase("unknown")) {
			remoteIp = request.getHeader("Proxy-Client-IP");
			if (remoteIp == null || remoteIp.length() == 0
					|| remoteIp.equalsIgnoreCase("unknown")) {
				remoteIp = request.getHeader("WL-Proxy-Client-IP");
				if (remoteIp == null || remoteIp.length() == 0
						|| remoteIp.equalsIgnoreCase("unknown")) {
					remoteIp = request.getRemoteAddr();
				}
			}
		}
		return remoteIp;
	}
}
