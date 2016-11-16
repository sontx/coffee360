package com.dutproject.coffee360.service.v1;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dutproject.coffee360.model.bean.PhotoReport;
import com.dutproject.coffee360.model.bean.PlaceReport;
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
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPlaceReports(
			@QueryParam("fromIndex") int fromIndex,
			@QueryParam("toIndex") int toIndex) {
		List<PlaceReport> reports;
		try {
			reports = reportBO.getPlaceReports(fromIndex, toIndex);
			GenericEntity<List<PlaceReport>> entity = new GenericEntity<List<PlaceReport>>(reports) {};
			return Response.status(200).entity(entity).build();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
	
	@GET
	@Path("/place/getone")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPlaceReport(
			@QueryParam("id") int id) {
		try {
			Report report = reportBO.getPlaceReport(id);
			return Response.status(200).entity(report).build();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
	
	@GET
	@Path("/place/quantity")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPlaceQuantity(
			@QueryParam("id") int id) {
		try {
			int count = reportBO.getPlaceQuantity(id);
			PrimitiveType<Integer> integerType = new PrimitiveType<>();
			integerType.setValue(count);
			return Response.status(200).entity(integerType).build();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}

	@GET
	@Path("/place/count")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPlacesCount() {		
		try {
			int count = reportBO.getPlacesCount();
			PrimitiveType<Integer> integerType = new PrimitiveType<>();
			integerType.setValue(count);
			return Response.status(200).entity(integerType).build();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}	

	@GET
	@Path("/state")
	@Produces(MediaType.APPLICATION_JSON)
	public Response setReportState(@QueryParam("id") int id, @QueryParam("state") String state) {
		try {
			reportBO.setReportState(id, state);
			return Response.ok().build();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
	
	/** ------------------------------- photo ------------------------------------ */
	
	@GET
	@Path("/photo/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPhotoReports(
			@QueryParam("fromIndex") int fromIndex,
			@QueryParam("toIndex") int toIndex) {
		try {
			List<PhotoReport> reports = reportBO.getPhotoReports(fromIndex, toIndex);
			GenericEntity<List<PhotoReport>> entity = new GenericEntity<List<PhotoReport>>(reports) {};
			return Response.status(200).entity(entity).build();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
	
	@GET
	@Path("/photo/getone")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPhotoReport(
			@QueryParam("id") int id) {
		try {
			Report report = reportBO.getPhotoReport(id);
			return Response.status(200).entity(report).build();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
	
	@GET
	@Path("/photo/quantity")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPhotoQuantity(
			@QueryParam("id") int id) {
		try {
			int count = reportBO.getPhotoQuantity(id);
			PrimitiveType<Integer> integerType = new PrimitiveType<>();
			integerType.setValue(count);
			return Response.status(200).entity(integerType).build();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
	
	@GET
	@Path("/photo/count")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPhotosCount() {		
		try {
			int count = reportBO.getPhotosCount();
			PrimitiveType<Integer> integerType = new PrimitiveType<>();
			integerType.setValue(count);
			return Response.status(200).entity(integerType).build();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}	
	
}
