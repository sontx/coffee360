package com.dutproject.coffee360.service.v1;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dutproject.coffee360.model.bean.Place;
import com.dutproject.coffee360.model.bean.PrimitiveType;
import com.dutproject.coffee360.model.bo.PlaceBO;

@Path("/v1/place")
public class PlaceService {
	private PlaceBO placeBO = new PlaceBO();

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response addPlace(
			@QueryParam("accessToken") String accessToken, 
			Place place) {
		Place result = placeBO.addPlace(place);
		return Response.status(200).entity(result).build();
	}

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_XML)
	public Response getPlaces(
			@QueryParam("latitude") double locationLat,
			@QueryParam("longitude") double locationLng,
			@QueryParam("radius") double radius) {
		ArrayList<Place> places = placeBO.getPlaces(locationLat, locationLng, radius);
		GenericEntity<ArrayList<Place>> entity = new GenericEntity<ArrayList<Place>>(places) {};
		return Response.status(200).entity(entity).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Response getPlace(@PathParam("id") int id) {
		Place place = placeBO.getPlace(id);
		return Response.status(200).entity(place).build();
	}
	
	@DELETE
	@Path("/del")
	@Produces(MediaType.APPLICATION_XML)
	public Response deletePlace(
			@QueryParam("accessToken") String accessToken, 
			@QueryParam("id") int id) {
		boolean result = placeBO.deletePlace(id);
		PrimitiveType<Boolean> booleanType = new PrimitiveType<>();
		booleanType.setValue(result);
		return Response.status(200).entity(booleanType).build();
	}
}
