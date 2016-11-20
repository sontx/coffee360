package com.dutproject.coffee360admin.model.dao;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import com.dutproject.coffee360.model.bean.Address;
import com.dutproject.coffee360.model.bean.Place;

public class PlaceDAO extends BaseDAO {
	private static final String PATH = getPath("Coffee360Service/rest/v1/place");
	private WebTarget target = ClientBuilder.newClient().target(PATH);

	public Place getPlace(int placeId) {
		Response response = target
				.path("/{placeId}")
				.resolveTemplate("placeId", placeId)
				.request(MediaType.APPLICATION_JSON)
				.header("Authorization", getAuthorizationString())
				.get();
		if (isSuccessful(response.getStatus())) {
			String jsonString = response.readEntity(String.class);
			Place place = toPlace(new JSONObject(jsonString));
			return place;
		}
		return null;
	}

	private Place toPlace(JSONObject jsonObject) {
		Address address = toAddress(jsonObject.getJSONObject("address"));
		String createdTime = jsonObject.getString("createdTime");
		int creatorId = jsonObject.getInt("creatorId");
		String description = jsonObject.getString("description");
		int id = jsonObject.getInt("id");
		String name = jsonObject.getString("name");
		int ownerId = jsonObject.getInt("ownerId");
		int[] tagIds = toTagIds(jsonObject.getJSONArray("tagIds"));
		int thumbnailId = jsonObject.getInt("thumbnailId");
		return new Place(id, name, address, tagIds, description, thumbnailId);
	}

	private int[] toTagIds(JSONArray jsonArray_tagIds) {
		int countTagIds = jsonArray_tagIds.length();
		int[] tagIds = new int[countTagIds];
		for (int i = 0; i < countTagIds; ++i) {
			tagIds[i] = (int) jsonArray_tagIds.get(i);
		}
		return tagIds;
	}

	private Address toAddress(JSONObject json_address) {
		int id = json_address.getInt("id");
		String name = json_address.getString("name");
		double locationLat = json_address.getDouble("locationLat");
		double locationLng = json_address.getDouble("locationLng");
		return new Address(id, name, locationLat, locationLng);
	}

	public boolean update(Place place) {
		Response response = target
				.path("/update")
				.request(MediaType.APPLICATION_JSON)
				.header("Authorization", getAuthorizationString())
				.put(Entity.entity(new JSONObject(place).toString(), MediaType.APPLICATION_JSON));
		if (isSuccessful(response.getStatus())) {
			return true;
		}
		return false;
	}

}
