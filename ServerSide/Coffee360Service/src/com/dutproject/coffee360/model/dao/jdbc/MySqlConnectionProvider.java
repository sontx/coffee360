package com.dutproject.coffee360.model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnectionProvider implements IConnectionProvider {
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private Connection connection;

	public MySqlConnectionProvider(String host, String databaseName, String username, String password)
			throws ClassNotFoundException, SQLException {
		Class.forName(JDBC_DRIVER);
		String url = String.format("jdbc:mysql://%s/%s", host, databaseName);
		connection = DriverManager.getConnection(url, username, password);
	}

	@Override
	public Connection getConnection() {
		return connection;
	}

}
