package com.myutils.hbase;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HConnectionManager;
import org.apache.hadoop.hbase.client.HTable;

public class DfdmpConnmini {
	
	private static Configuration hcfg = HBaseConfiguration.create();
	static loadConf lcnf = null;
	static ArrayList<HConnection> listHconn = new ArrayList<HConnection>();
	
	public static String dftt_dmp_userinfo = null;
	
	static {

		try {
			lcnf = new loadConf();

			System.out.println(lcnf.getProperty("zookeeperaddruserinfo"));
			System.out.println(lcnf.getProperty("zookeeperport"));
			System.out.println(Integer.parseInt(lcnf.getProperty("concnt")));
			hcfg.set("hbase.zookeeper.quorum",lcnf.getProperty("zookeeperaddruserinfo"));
			hcfg.set("hbase.zookeeper.property.clientPort",lcnf.getProperty("zookeeperport"));
			createHconnection(Integer.parseInt(lcnf.getProperty("concnt")));
			dftt_dmp_userinfo=lcnf.getProperty("dftt_dmp_userinfo");
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static HTable gethbasetable(String _tblname) throws Exception {
		return (HTable) getConn().getTable(_tblname);
	}

	public static String getHtblName(String _key) {
		if (lcnf.chkProperty(_key))
			return lcnf.getProperty(_key);
		else
			return "";
	}

	public static void createHconnection(int _num) throws Exception {
		for (int i = 0; i < _num; i++) {
			hcfg.setInt("hbase.client.instance.id", i);
			listHconn.add(HConnectionManager.createConnection(hcfg));
		}
	}

	public static HConnection getConn() {
		int id = (int) (System.currentTimeMillis() % listHconn.size());
		return listHconn.get(id);
	}
}
