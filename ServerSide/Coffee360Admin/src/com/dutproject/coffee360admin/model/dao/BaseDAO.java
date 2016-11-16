package com.dutproject.coffee360admin.model.dao;

public abstract class BaseDAO {
	protected static final String HOST = "sontx.no-ip.org";
	protected static final int PORT = 8080;

	protected static String getPath(String path) {
		return String.format("http://%s:%d/%s", HOST, PORT, path);
	}
	
	protected String getAuthorizationString() {
		return String.format("Bearer %s", AdminAccountDAO.getAccessToken());
	}
	
	protected boolean isSuccessful(int status) {
		return 200 == status;
	}

}
