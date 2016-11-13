package com.dutproject.coffee360admin.model.dao;

public abstract class BaseDAO {
	protected static final String HOST = "localhost";
	protected static final int PORT = 8080;
	protected static final String AUTHORITY = String.format("%s:%d", HOST, PORT);

	protected boolean isSuccessful(int status) {
		return 200 == status;
	}

}
