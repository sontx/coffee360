package com.dutproject.coffee360admin.model.dao;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dutproject.coffee360.model.bean.Place;

public class PlaceDAO extends BaseDAO {
	private static final String PATH = getPath("Coffee360Service/rest/v1/place");
	private Client client = ClientBuilder.newClient();

	public Place getPlace(int placeId) {
		Response response = client
				.target(PATH)
				.path("/{placeId}")
				.resolveTemplate("placeId", placeId)
				.request(MediaType.APPLICATION_XML)
				.header("Authorization", getAuthorizationString())
				.get();
		if (isSuccessful(response.getStatus())) {
			Place place = response.readEntity(Place.class);
			return place;
		}
		return null;
	}

	public boolean update(Place place) {
		Response response = client
				.target(PATH)
				.path("/update")
				.request(MediaType.APPLICATION_XML)
				.header("Authorization", getAuthorizationString())
				.put(Entity.entity(place, MediaType.APPLICATION_XML));
		if (isSuccessful(response.getStatus())) {
			return true;
		}
		return false;
	}

}
