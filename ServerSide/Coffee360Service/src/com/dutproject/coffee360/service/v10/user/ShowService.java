package com.dutproject.coffee360.service.v10.user;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dutproject.coffee360.model.bean.*;
import com.dutproject.coffee360.model.bo.PlaceBO;

@Path("/v10/user/show")
public class ShowService {
	private PlaceBO placeBO = new PlaceBO();

	@GET
	@Path("/places")
	@Produces(MediaType.APPLICATION_XML)
	public ArrayList<Place> getPlaces(
			@QueryParam("latitude") double locationLat, 
			@QueryParam("longitude") double locationLng,
			@QueryParam("radius") double radius) {
		ArrayList<Place> places = placeBO.getPlaces(locationLat, locationLng, radius);
		return places;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Response getPlace(@PathParam("id") int id) {
		Place place = placeBO.getPlace(id);
		return Response.status(200).entity(place).build();
	}

}
