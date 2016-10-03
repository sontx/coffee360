package com.dutproject.coffee360.service.v10.user;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.dutproject.coffee360.model.bean.*;
import com.dutproject.coffee360.model.bo.PlaceBO;

@Path("/v10/user/show")
public class ShowService {
	private PlaceBO placeBO = new PlaceBO();
	
	@GET
	@Path("/place")
	@Produces(MediaType.APPLICATION_XML)
	public ArrayList<Place> getPlaces(RequestPlace requestPlace){
		return placeBO.getPlaces(requestPlace);
	}
	
}
