package com.dutproject.coffee360admin.model.dao;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.dutproject.coffee360.model.bean.Tag;

public class TagDAO extends BaseDAO {
	private static final String PATH = getPath("Coffee360Service/rest/v1/place/tag");
	private static final String PATH_2 = getPath("Coffee360Service/rest/v1/place/mtag");
	private Client client = ClientBuilder.newClient();
	private WebTarget target = client.target(PATH);
	private WebTarget target2 = client.target(PATH_2);

	public Tag getTag(int tagId) {
		Response response = target
				.queryParam("id", tagId)
				.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.get();
		if (isSuccessful(response.getStatus())) {
			String jsonString = response.readEntity(String.class);
			Tag tag = toTag(new JSONObject(jsonString));
			return tag;
		}
		return null;
	}

	private Tag toTag(JSONObject jsonObject) {
		int id = jsonObject.getInt("id");
		String name = jsonObject.getString("name");
		Tag tag = new Tag(id, name);
		return tag;
	}

	public int getId(String tagName) {
		Response response = target2
				.queryParam("name", tagName)
				.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.get();
		if (isSuccessful(response.getStatus())) {
			String jsonString = response.readEntity(String.class);
			int id = toId(new JSONObject(jsonString));
			return id;
		}
		return 0;
	}

	private int toId(JSONObject jsonObject) {
		return jsonObject.getInt("id");
	}

}
