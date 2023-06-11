package com.ssafy.trip.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AttractionDBUtil {
//	static final String URL = "jdbc:mysql://127.0.0.1:3306/enjoytrip?serverTimezone=UTC";
//	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
//	static final String ID = "ssafy";
//	static final String PASSWORD = "ssafy";
//	
//	private static AttractionDBUtil instance = new AttractionDBUtil();
//	
//	public static AttractionDBUtil getInstance() {
//		return instance;
//	}
//	
//	private AttractionDBUtil() {
//		try {
//			Class.forName(DRIVER);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	
//	public Connection getConnection() throws SQLException {
//		return DriverManager.getConnection(URL, ID, PASSWORD);
//	}
	
	public void close(AutoCloseable... closeables) {
		for (AutoCloseable c : closeables) {
			if (c != null) {
				try {
					c.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
