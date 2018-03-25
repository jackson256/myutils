package com.myutils.str;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class OpUtils {

	public static <V> V check(V v, V defValue) {
		return v == null || v == "" || v == "null" || "".equals(v) || "null".equals(v) ? defValue : v;
	}

	private static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String LETTERCHAR = "abcdefghijkllmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	/**
	 * ����һ�������Ĺ̶�ǰ׺������ַ�
	 * 
	 * @param length
	 *            ����ַ���
	 * @return ����ַ�
	 */
	public static String generateMixString(String prefix, int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(ALLCHAR.charAt(random.nextInt(LETTERCHAR.length())));
		}
		return prefix + sb.toString().toLowerCase() + String.valueOf(System.currentTimeMillis()).substring(8)
				+ (long) (Math.random() * 10);
	}

	
	/**
	 * ��� "yyMMddHHmmss+3λ�����" ���ַ� 
	 * @return
	 */
	public static String generateId() {
		String rand = "";
		for (int i = 0; i < 3; i++) {
			rand += String.valueOf((int) (Math.random() * 10));
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		String date = sdf.format(new Date());
		String id = date + rand;
		return id;
	}
	
	/**
	 * 判断纯数字
	 * @param maybeNumeric
	 * @return
	 */
	public static boolean isNumeric(String maybeNumeric) {
	    return maybeNumeric != null && maybeNumeric.matches("[0-9]+");
	}

}
