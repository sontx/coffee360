package com.dutproject.coffee360.service.v10.user;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dutproject.coffee360.model.bean.Place;
import com.dutproject.coffee360.model.bo.PlaceBO;

@Path("/v10/user/place")
public class PlaceService {
	private PlaceBO placeBO = new PlaceBO();
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response addPlace(
			@QueryParam("accessToken") String accessToken,
			Place place){
		Place result = placeBO.addPlace(place);
		return Response.status(200).entity(result).build();
	}
}
