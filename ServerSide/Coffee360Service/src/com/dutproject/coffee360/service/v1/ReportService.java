package com.dutproject.coffee360.service.v1;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dutproject.coffee360.model.bean.PrimitiveType;
import com.dutproject.coffee360.model.bean.Report;
import com.dutproject.coffee360.model.bo.ReportBO;
import com.dutproject.coffee360.service.Role;
import com.dutproject.coffee360.service.Secured;

@Path("/v1/report")
@Secured({Role.ROLE_ADMIN})
public class ReportService {
	private ReportBO reportBO = new ReportBO();

	@GET
	@Path("/place/get")
	@Produces(MediaType.APPLICATION_XML)
	public Response getPlaceReports(
			@QueryParam("fromIndex") int fromIndex,
			@QueryParam("toIndex") int toIndex) {
		List<Report> reports = reportBO.getPlaceReports(fromIndex, toIndex);
		GenericEntity<List<Report>> entity = new GenericEntity<List<Report>>(reports) {};
		return Response.status(200).entity(entity).build();
	}
	
	@GET
	@Path("/place/getone")
	@Produces(MediaType.APPLICATION_XML)
	public Response getPlaceReports(
			@QueryParam("id") int id) {
		Report report = reportBO.getPlaceReport(id);
		return Response.status(200).entity(report).build();
	}
	
	@GET
	@Path("/place/quantity")
	@Produces(MediaType.APPLICATION_XML)
	public Response getPlaceQuantity(
			@QueryParam("id") int id) {
		int count = reportBO.getPlaceQuantity(id);
		PrimitiveType<Integer> integerType = new PrimitiveType<>();
		integerType.setValue(count);
		return Response.status(200).entity(integerType).build();
	}

	@GET
	@Path("/place/count")
	@Produces(MediaType.APPLICATION_XML)
	public Response getPlacesCount() {
		int count = reportBO.getPlacesCount();
		PrimitiveType<Integer> integerType = new PrimitiveType<>();
		integerType.setValue(count);
		return Response.status(200).entity(integerType).build();
	}	
}
