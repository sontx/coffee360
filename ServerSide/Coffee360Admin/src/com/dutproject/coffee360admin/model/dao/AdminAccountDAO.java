package com.dutproject.coffee360admin.model.dao;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dutproject.coffee360admin.model.bean.AdminAccount;

public class AdminAccountDAO extends BaseDAO {
	private static final String PATH = getPath("Coffee360Service/rest/v1/auth");
	private static String ACCESS_TOKEN = null;
	private WebTarget target = ClientBuilder.newClient().target(PATH);

	public boolean isValidAccount(AdminAccount account) {
		Response response = target
			.path("/admin")
			.request()
			.post(Entity.entity(account.toJson(), MediaType.APPLICATION_JSON));
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
