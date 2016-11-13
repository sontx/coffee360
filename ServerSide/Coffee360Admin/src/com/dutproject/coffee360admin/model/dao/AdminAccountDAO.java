package com.dutproject.coffee360admin.model.dao;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dutproject.coffee360admin.model.bean.AdminAccount;

public class AdminAccountDAO extends BaseDAO {
	private static final String PATH = "/Coffee360Service/rest/v1/auth";
	private static final String AUTH_URL = String.format("http://%s%s", AUTHORITY, PATH);
	private static String ACCESS_TOKEN = null;

	public boolean isValidAccount(AdminAccount account) {
		Client client = ClientBuilder.newClient();
		
		Response response = client
			.target(AUTH_URL)
			.path("/admin")
			.request(MediaType.APPLICATION_XML)
			.post(Entity.entity(account, MediaType.APPLICATION_XML));
		
		if (isSuccessful(response.getStatus())) {
			String accessToken = response.readEntity(String.class);
			saveAccessToken(accessToken);
			return true;
		}
		
		return false;
	}

	private void saveAccessToken(String accessToken) {
		ACCESS_TOKEN = accessToken;
	}

	public static String getAccessToken() {
		return ACCESS_TOKEN;
	}

}
