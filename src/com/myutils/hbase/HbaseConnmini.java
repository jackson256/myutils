package com.myutils.hbase;


import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HConnectionManager;
import org.apache.hadoop.hbase.client.HTable;

public class HbaseConnmini {

	private static Configuration hcfg = HBaseConfiguration.create();
	static loadConf lcnf = null;
	static ArrayList<HConnection> listHconn = new ArrayList<HConnection>();
	
	public static String htb_uservisited = null;
	
	static {

		try {
			lcnf = new loadConf();

			System.out.println(lcnf.getProperty("zookeeperaddr"));
			System.out.println(lcnf.getProperty("zookeeperport"));
			System.out.println(Integer.parseInt(lcnf.getProperty("concnt")));
			hcfg.set("hbase.zookeeper.quorum",lcnf.getProperty("zookeeperaddr"));
			hcfg.set("hbase.zookeeper.property.clientPort",lcnf.getProperty("zookeeperport"));
			createHconnection(Integer.parseInt(lcnf.getProperty("concnt")));
			htb_uservisited=lcnf.getProperty("htb_uservisited");
			
			
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