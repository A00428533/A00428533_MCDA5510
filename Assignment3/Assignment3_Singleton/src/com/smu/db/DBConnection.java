package com.smu.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	static Connection connection = null;

	private DBConnection() {
	}

	public static Connection getDBConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			connection = DriverManager.getConnection(
					"jdbc:mysql://dev.cs.smu.ca/a_gupta?user=a_gupta&password=A00428533&useSSL=false&AllowPublicKeyRetrieval=True&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");

		} catch (Exception e) {

			System.out.println("Error setting up connection");
			e.printStackTrace();
		}

		return connection;
	}
}
