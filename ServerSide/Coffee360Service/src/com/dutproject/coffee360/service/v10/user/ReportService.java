package com.dutproject.coffee360.service.v10.user;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.dutproject.coffee360.model.bean.IntegerValue;
import com.dutproject.coffee360.model.bean.Report;
import com.dutproject.coffee360.model.bo.ReportBO;

@Path("/v10/user/report")
public class ReportService {
	private ReportBO reportBO = new ReportBO();
	
	@GET
	@Path("/places")
	@Produces(MediaType.APPLICATION_XML)
	public List<Report> getPlaceReports(
			@PathParam("accessToken") String accessToken, 
			@PathParam("fromIndex") int fromIndex,
			@PathParam("toIndex") int toIndex) {
		return reportBO.getPlaceReports(fromIndex, toIndex);
	}
	
	@GET
	@Path("/placesCount")
	@Produces(MediaType.APPLICATION_XML)
	public IntegerValue getPlacesCount(
			@PathParam("accessToken") String accessToken) {
		int count = reportBO.getPlacesCount();
		IntegerValue value = new IntegerValue();
		value.setValue(count);
		return value;
	}
}
