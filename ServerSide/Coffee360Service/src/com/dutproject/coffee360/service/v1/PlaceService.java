package com.dutproject.coffee360.service.v1;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.dutproject.coffee360.model.bean.Place;
import com.dutproject.coffee360.model.bean.PrimitiveType;
import com.dutproject.coffee360.model.bean.Tag;
import com.dutproject.coffee360.model.bean.XmlInteger;
import com.dutproject.coffee360.model.bo.PlaceBO;
import com.dutproject.coffee360.service.Role;
import com.dutproject.coffee360.service.Secured;

@Path("/v1/place")
public class PlaceService extends BaseService {
	private PlaceBO placeBO = new PlaceBO();

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Secured({ Role.ROLE_USER })
	public Response addPlace(Place place, @Context SecurityContext securityContext) {
		int accountId = getAccountId(securityContext);
		Place result = placeBO.addPlace(accountId, place);
		return Response.status(200).entity(result).build();
	}

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPlaces(@QueryParam("latitude") double locationLat, @QueryParam("longitude") double locationLng,
			@QueryParam("radius") double radius) {
		ArrayList<Place> places = placeBO.getPlaces(locationLat, locationLng, radius);
		GenericEntity<ArrayList<Place>> entity = new GenericEntity<ArrayList<Place>>(places) {
		};
		return Response.status(200).entity(entity).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPlace(@PathParam("id") int id) {
		Place place = placeBO.getPlace(id);
		return Response.status(200).entity(place).build();
	}

	@DELETE
	@Path("/del")
	@Produces(MediaType.APPLICATION_JSON)
	@Secured({ Role.ROLE_USER })
	public Response deletePlace(@QueryParam("id") int id) {
		boolean result = placeBO.deletePlace(id);
		PrimitiveType<Boolean> booleanType = new PrimitiveType<>();
		booleanType.setValue(result);
		return Response.status(200).entity(booleanType).build();
	}
	
	@PUT
	@Path("update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Secured({ Role.ROLE_USER, Role.ROLE_ADMIN })
	public Response updatePlace(Place place) {
		try {
			Place result = placeBO.updatePlace(place);
			return Response.ok(result).build();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
	
	@GET
	@Path("photos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPlacePhotos(@QueryParam("id") int id) {
		try {
			ArrayList<XmlInteger> result = placeBO.getPlacePhotos(id);
			GenericEntity<ArrayList<XmlInteger>> entity = new GenericEntity<ArrayList<XmlInteger>>(result) {
			};
			return Response.ok(entity).build();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
	

	@GET
	@Path("tag")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTag(@QueryParam("id") int id) {
		try {
			Tag tag = placeBO.getTagById(id);
			return Response.ok().entity(tag).build();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
	
	@GET
	@Path("mtag")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTag(@QueryParam("name") String name) {
		try {
			Tag tag = placeBO.getTagByName(name);
			return Response.ok().entity(tag).build();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
}
