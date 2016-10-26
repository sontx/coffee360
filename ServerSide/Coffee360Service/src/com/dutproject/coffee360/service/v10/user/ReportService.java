package com.dutproject.coffee360.service.v10.user;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dutproject.coffee360.model.bean.PrimitiveType;
import com.dutproject.coffee360.model.bean.Report;
import com.dutproject.coffee360.model.bo.ReportBO;

@Path("/v10/user/report")
public class ReportService {
	private ReportBO reportBO = new ReportBO();

	@GET
	@Path("/places")
	@Produces(MediaType.APPLICATION_XML)
	public Response getPlaceReports(
			@QueryParam("accessToken") String accessToken, 
			@QueryParam("fromIndex") int fromIndex,
			@QueryParam("toIndex") int toIndex) {
		List<Report> reports = reportBO.getPlaceReports(fromIndex, toIndex);
		return Response.status(200).entity(reports).build();
	}

	@GET
	@Path("/placesCount")
	@Produces(MediaType.APPLICATION_XML)
	public Response getPlacesCount(@QueryParam("accessToken") String accessToken) {
		int count = reportBO.getPlacesCount();
		PrimitiveType<Integer> integerType = new PrimitiveType<>();
		integerType.setValue(count);
		return Response.status(200).entity(integerType).build();
	}
	
	@DELETE
	@Path("/delete")
	@Produces(MediaType.APPLICATION_XML)
	public Response deletePlace(@QueryParam("accessToken") String accessToken, @QueryParam("id") int id) {
		boolean result = reportBO.deletePlace(id);
		PrimitiveType<Boolean> booleanType = new PrimitiveType<>();
		booleanType.setValue(result);
		return Response.status(200).entity(booleanType).build();
	}
}
