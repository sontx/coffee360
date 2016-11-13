package com.dutproject.coffee360admin.model.dao;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dutproject.coffee360.model.bean.Place;

public class PlaceDAO extends BaseDAO {

	private static final String PATH = "/Coffee360Service/rest/v1/place";
	private static final String PLACE = "http://localhost:8080/Coffee360Service/rest/v1/place/3";
	private Client client = ClientBuilder.newClient();

	public Place getPlace(int placeId) {
		Response response = client
				.target("http://localhost:8080/Coffee360Service/rest/v1/place")
				.path("/{placeId}")
				.resolveTemplate("placeId", placeId)
				.request(MediaType.APPLICATION_XML)
				.header("Authorization", String.format("Bearer %s", AdminAccountDAO.getAccessToken()))
				.get();
		Place place = response.readEntity(Place.class);
		return place;
	}

}
