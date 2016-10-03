package com.dutproject.coffee360.model.dao.jdbc;

import java.sql.SQLException;

public final class DatabaseManager {
	private static DatabaseManager instance = null;

	private IConnectionProvider connectionProvider;

	public static DatabaseManager getInstance() {
		if (instance == null)
			instance = new DatabaseManager();
		return instance;
	}

	public IConnectionProvider getConnectionProvider() {
		return connectionProvider;
	}

	public void release() {
		if (connectionProvider != null) {
			try {
				connectionProvider.getConnection().close();
			} catch (SQLException ignored) {
			} finally {
				connectionProvider = null;
			}
		}
	}

	private DatabaseManager() {
		try {
			connectionProvider = new MySqlConnectionProvider("localhost", "coffee360", "root", "");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
