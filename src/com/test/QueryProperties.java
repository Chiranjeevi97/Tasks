package com.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class QueryProperties {

	private static Properties prop = null;
	public QueryProperties() {
		try {
			String filepath = "E:/Eclipse/workspace/LoginTask/src/queries.properties";
			FileInputStream fis = new FileInputStream(filepath);
			prop = new Properties();
			prop.load(fis);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	public static Properties getProp() {
		if(prop == null)
			new QueryProperties();
		return prop;
	}
}
