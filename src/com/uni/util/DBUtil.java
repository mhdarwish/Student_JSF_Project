package com.uni.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBUtil {
	private final static String url = "jdbc:oracle:thin:@192.168.0.109:1521/traffic";
	private final static String name = "traffic";
	private final static String password = "traffic";
	private static java.sql.Connection conn = null;

	public static Connection getConnection() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, name, password);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void closeOperations(Connection conn, PreparedStatement stmt) {
		try {
			stmt.close();
			conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public static void doRollBack(Connection conn) {
		try {
			conn.rollback();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
