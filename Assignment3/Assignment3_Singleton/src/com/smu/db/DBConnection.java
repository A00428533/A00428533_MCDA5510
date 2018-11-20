package com.smu.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	Connection connection = null;

	public DBConnection() {
		setupDBConnection();
	}

	public Connection getConnection() {
		return connection;
	}

	final private Connection setupDBConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			this.connection = DriverManager.getConnection(
					"jdbc:mysql://dev.cs.smu.ca/a_gupta?user=a_gupta&password=A00428533&useSSL=false&AllowPublicKeyRetrieval=True&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");

		} catch (Exception e) {

			System.out.println("Error setting up connection");
			e.printStackTrace();
		}

		return connection;
	}
}
