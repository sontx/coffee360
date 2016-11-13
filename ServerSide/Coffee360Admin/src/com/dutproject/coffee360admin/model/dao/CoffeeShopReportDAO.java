package com.dutproject.coffee360admin.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dutproject.coffee360.model.bean.Place;
import com.dutproject.coffee360.model.bean.PrimitiveType;
import com.dutproject.coffee360.model.bean.Report;
import com.dutproject.coffee360admin.controller.shop.CoffeeShopReportServlet;
import com.dutproject.coffee360admin.model.bean.ShopReport;
import com.dutproject.coffee360admin.model.bean.NumberOfReport;

public class CoffeeShopReportDAO extends BaseDAO {
	private static final String PATH = "/Coffee360Service/rest/v1/report/place";
	private static final String NUMBER_OF_REPORTS = String.format("http://%s%s", AUTHORITY, PATH);
	private static final String REPORTS = "http://localhost:8080/Coffee360Service/rest/v1/report/place/get?fromIndex=%d&toIndex=%d";
	private static final String REPORT = "http://localhost:8080/Coffee360Service/rest/v1/report/place/getone?id=%d";
	private static final String QUANTITY = "http://localhost:8080/Coffee360Service/rest/v1/report/place/quantity?id=%d";
	private Client client = ClientBuilder.newClient();
	private PlaceDAO placeDAO = new PlaceDAO();

	public int getNumberOfCoffeeShopReport() {
		Response response = client
			.target(NUMBER_OF_REPORTS)
			.path("/count")
			.request(MediaType.APPLICATION_XML)
			.header("Authorization", String.format("Bearer %s", AdminAccountDAO.getAccessToken()))
			.get(Response.class);
		
		if (isSuccessful(response.getStatus())) {
			PrimitiveType result = response.readEntity(new PrimitiveType<Integer>().getClass());
			return (int) result.getValue();
		}
		return 0;
	}

	public List<ShopReport> getListCoffeeShopReports(int pageNumber) {
		if (pageNumber <= 0) {
			return null;
		}
		
		int totalOfCoffeeShopReport = getNumberOfCoffeeShopReport();
		int firstIndex = 1 + (pageNumber - 1) * CoffeeShopReportServlet.MAX_ENTRIES_PER_PAGE;
		int secondIndex = (firstIndex + CoffeeShopReportServlet.MAX_ENTRIES_PER_PAGE - 1);
		if (secondIndex > totalOfCoffeeShopReport) {
			secondIndex = totalOfCoffeeShopReport;
		}
		
		List<ShopReport> listCoffeeShopReports = new ArrayList<ShopReport>();
		
		List<Report> reports = getReports(firstIndex, secondIndex);
		for (Report report : reports) {
			Place place = getPlace(report.getPlaceId());
			
			int reportId = report.getId();
			String placeName = place.getName();
			String placeDescription = place.getDescription();
			int placeReportQuantity = getPlaceQuantity(place.getId());
			listCoffeeShopReports.add(new ShopReport(reportId, placeName, placeDescription, placeReportQuantity));
		}
		
		return listCoffeeShopReports;
	}
	
	private List<Report> getReports(int fromIndex, int toIndex) {
		Response response = client
			.target(String.format(REPORTS, fromIndex, toIndex))
			.request(MediaType.APPLICATION_XML)
			.header("Authorization", String.format("Bearer %s", AdminAccountDAO.getAccessToken()))
			.get();
		
		if (isSuccessful(response.getStatus())) {
			GenericType<List<Report>> list = new GenericType<List<Report>>(){};
			List<Report> reports = response.readEntity(list);
			return reports;
		}
		return null;
	}

	private Place getPlace(int placeId) {
		return placeDAO.getPlace(placeId);
	}
	
	private int getPlaceQuantity(int placeId) {
		Client client = ClientBuilder.newClient();
		Response response = client
				.target(String.format(QUANTITY, placeId))
				.request(MediaType.APPLICATION_XML)
				.header("Authorization", "Bearer " + AdminAccountDAO.getAccessToken())
				.get();
		if (isSuccessful(response.getStatus())) {
			PrimitiveType result = response.readEntity(new PrimitiveType<Integer>().getClass());
			return (int) result.getValue();
		}
		return 0;
	}

	public ShopReport getReportByReportId(int id) {
		Client client = ClientBuilder.newClient();
		Response response = client.target(String.format(REPORT, id))
							.request(MediaType.APPLICATION_XML)
							.header("Authorization", "Bearer " + AdminAccountDAO.getAccessToken())
							.accept(MediaType.APPLICATION_XML)
							.get();
		ShopReport report = response.readEntity(ShopReport.class);
		return report;
	}
	
}
