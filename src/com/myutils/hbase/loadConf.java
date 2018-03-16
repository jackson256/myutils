package com.myutils.hbase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class loadConf {
	private static  Properties prop = null;
	static{
		try {
			prop =  new Properties();
			loadconf();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void loadconf() throws FileNotFoundException, IOException {
		String path = "";
		path = loadConf.class.getProtectionDomain().getCodeSource().getLocation().getFile();
		if (path.indexOf("WEB-INF") != -1) {
			// file:/app/tomcat/webapps/gsinput/WEB-INF/hbase.properties 去除file:
			if (path.startsWith("file:"))
				path = path.substring(5, path.indexOf("WEB-INF")) + "WEB-INF/hbase.properties";
			else
				path = path.substring(0, path.indexOf("WEB-INF")) + "WEB-INF/hbase.properties";
			prop.load(new FileInputStream(path));
		} else {
			System.out.println("-----1"+path);
			path = "com/myutils/hbase/hbase.properties";
			System.out.println("-----2"+path);
			
			prop.load(loadConf.class.getClassLoader().getResourceAsStream(path));
		}
	}

	public boolean chkProperty(String _key) {
		return prop.containsKey(_key);
	}

	public String getProperty(String _key) {
		return prop.getProperty(_key);
	}
}
