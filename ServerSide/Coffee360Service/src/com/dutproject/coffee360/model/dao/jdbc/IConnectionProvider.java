package com.dutproject.coffee360.model.dao.jdbc;

import java.sql.Connection;

public interface IConnectionProvider {
	Connection getConnection();
}
