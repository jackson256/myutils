package com.myutils.hbase;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

import com.myutils.str.OpUtils;
import com.myutils.str.StringUtils;

public class TestUtils {
	
	private static byte[] familyByte = "d".getBytes();
	private static byte[] provnameByte = "provname".getBytes();
	private static byte[] citynameByte = "cityname".getBytes();
	private static byte[] advfiler = "advfiler".getBytes();
	private static byte[] sex = "sex".getBytes();

	/**
	 * 向用户app安装表里赋值
	 * @param imei
	 * @param code
	 */
	public static void putInstallAppValue(String imei,String code,String value){
		HTableInterface htable = null;
		try {
			htable = DfdmpConnmini.gethbasetable(DfdmpConnmini.dftt_dmp_userinfo);
			Put put = new Put(imei.getBytes());
			put.add(familyByte, Bytes.toBytes(code), Bytes.toBytes(value));
			htable.put(put);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(htable!=null){
				try {
					htable.close();
					//htable.flushCommits();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 根据imei查询性别及app安装信息
	 * @param imei
	 * @param codeStr
	 * @return
	 */
	public static Map<String, String> getUserSexAndAppInstall(String imei, String codeStr) {
		Map<String,String> sexAndappInstall = new HashMap<>();
		HTableInterface htable = null;
		Result result = null;
		String[] codes = codeStr.split(",|，");
		try {
			htable = DfdmpConnmini.gethbasetable(DfdmpConnmini.dftt_dmp_userinfo);
			Get get = new Get(Bytes.toBytes(imei));
			get.addColumn(familyByte, sex);
			for (String code : codes) {
				get.addColumn(familyByte, Bytes.toBytes(code));
			}
			result = htable.get(get);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(htable != null){
				try {
					htable.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if(result.getValue(familyByte, sex)!=null){
			sexAndappInstall.put("sex", new String(result.getValue(familyByte, sex)));
		}
		for (String code : codes) {
			if(result.getValue(familyByte, Bytes.toBytes(code))!=null){
				sexAndappInstall.put(code, new String(result.getValue(familyByte, Bytes.toBytes(code))));
			}
		}
		return sexAndappInstall;
	}
	
	
	
	
	
	public static void main(String[] args) {
		//查询性别及安装应用
		Map<String,String> sexAndInstallApp = getUserSexAndAppInstall("867993023656620","1008");
		System.out.println(sexAndInstallApp);
		
		//给imei用户画像赋安装应用
//		putInstallAppValue("867993023656620","1008","1");
		
		//查询用户不喜爱标签
//		String nolike = checkUserInterest("863167038178300", "unionadv");//advfiler,unionadv
//		System.out.println("===nolike=====>" + nolike);
		
		//给imei用户画像赋"不喜爱"标签
//		putUserInterest("863167038178300", "unionadv", "1");
		
	}
	
	
	
	
}
