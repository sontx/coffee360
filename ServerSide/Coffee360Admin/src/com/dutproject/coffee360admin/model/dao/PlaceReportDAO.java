package com.dutproject.coffee360admin.model.dao;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dutproject.coffee360.model.bean.PrimitiveType;
import com.dutproject.coffee360.model.bean.PlaceReport;
import com.dutproject.coffee360admin.controller.place.PlaceReportServlet;

public class PlaceReportDAO extends BaseDAO {
	private static final String PATH = getPath("Coffee360Service/rest/v1/report/place");
	private Client client = ClientBuilder.newClient();

	@SuppressWarnings("rawtypes")
	public int getNumberOfReport() {
		Response response = client
				.target(PATH)
				.path("/count")
				.request(MediaType.APPLICATION_XML)
				.header("Authorization", getAuthorizationString())
				.get(Response.class);
		
		if (isSuccessful(response.getStatus())) {
			PrimitiveType result = response
					.readEntity(new PrimitiveType<Integer>().getClass());
			return (int) result.getValue();
		}
		return 0;
	}

	private List<PlaceReport> getListPlaceReports(int fromIndex, int toIndex) {
		Response response = client
				.target(String
						.format(PATH + "/get?fromIndex=%d&toIndex=%d",
								fromIndex, toIndex))
				.request(MediaType.APPLICATION_XML)
				.header("Authorization", getAuthorizationString())
				.get();
		
		if (isSuccessful(response.getStatus())) {
			GenericType<List<PlaceReport>> list = new GenericType<List<PlaceReport>>(){};
			List<PlaceReport> reports = response.readEntity(list);
			return reports;
		}
		return null;
	}

	public PlaceReport getReportByReportId(int id) {
		Response response = client
				.target(String.format(PATH + "/getone?id=%d", id))
				.request(MediaType.APPLICATION_XML)
				.header("Authorization", getAuthorizationString())
				.accept(MediaType.APPLICATION_XML)
				.get();
		PlaceReport report = response.readEntity(PlaceReport.class);
		return report;
	}

	public List<PlaceReport> getListReport(int pageNumber) {
		if (pageNumber <= 0) {
			return null;
		}
		
		int totalOfCoffeeShopReport = getNumberOfReport();
		int fromIndex = 1 + (pageNumber - 1) * PlaceReportServlet.MAX_ENTRIES_PER_PAGE;
		int toIndex = (fromIndex + PlaceReportServlet.MAX_ENTRIES_PER_PAGE - 1);
		if (toIndex > totalOfCoffeeShopReport) {
			toIndex = totalOfCoffeeShopReport;
		}
		
		return getListPlaceReports(fromIndex, toIndex);
	}
	
	@SuppressWarnings("rawtypes")
	public int getReportQuantity(int placeId) {
		Response response = client
				.target(String.format(PATH + "/quantity?id=%d", placeId))
				.request(MediaType.APPLICATION_XML)
				.header("Authorization", getAuthorizationString())
				.get();
		if (isSuccessful(response.getStatus())) {
			PrimitiveType result = response
					.readEntity(new PrimitiveType<Integer>().getClass());
			return (int) result.getValue();
		}
		return 0;
	}
	
}
