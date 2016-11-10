package com.dutproject.coffee360.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.dutproject.coffee360.model.dao.jdbc.DatabaseManager;
import com.dutproject.coffee360.model.dao.jdbc.IConnectionProvider;

public abstract class JdbcBaseDAO {
	protected final IConnectionProvider connectionProvider;

	protected synchronized int getLastRowId(Statement statement, String tableName, String idColumnName)
			throws SQLException {
		String sql = String.format("SELECT * FROM %s ORDER BY %s DESC LIMIT 1", tableName, idColumnName);
		ResultSet rs = statement.executeQuery(sql);
		int id = -1;
		if (rs.next())
			id = rs.getInt(idColumnName);
		rs.close();
		return id;
	}

	public JdbcBaseDAO() {
		connectionProvider = DatabaseManager.getInstance().getConnectionProvider();
	}
}
