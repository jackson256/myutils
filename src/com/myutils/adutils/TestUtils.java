package com.myutils.adutils;

public class TestUtils {
	
	public static void main(String[] args) {
		long num1 = 12L;
		long num2 = 0l;
		System.out.println(Long.toBinaryString(num1));
		System.out.println(num2 = (num1 << 8));
		System.out.println(Long.toBinaryString(num2));
	}

}
