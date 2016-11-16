package com.dutproject.coffee360admin.model.dao;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dutproject.coffee360.model.bean.PhotoReport;
import com.dutproject.coffee360.model.bean.PrimitiveType;
import com.dutproject.coffee360admin.controller.photo.PhotoReportServlet;

public class PhotoReportDAO extends BaseDAO {
	private static final String PATH = getPath("Coffee360Service/rest/v1/report/photo");
	private Client client = ClientBuilder.newClient();

	@SuppressWarnings("rawtypes")
	public int getNumberOfReport() {
		Client client = ClientBuilder.newClient();
		Response response = client
				.target(PATH)
				.path("/count")
				.request(MediaType.APPLICATION_XML)
				.header("Authorization", getAuthorizationString())
				.get();
		if (isSuccessful(response.getStatus())) {
			PrimitiveType result = response.readEntity(PrimitiveType.class);
			return (int) result.getValue();
		}
		return 0;
	}

	public PhotoReport getReportById(int reportId) {
		Client client = ClientBuilder.newClient();
		Response response = client
				.target(String.format(PATH + "/getone?id=%d", reportId))
				.request(MediaType.APPLICATION_XML)
				.header("Authorization", getAuthorizationString())
				.get();
		if (isSuccessful(response.getStatus())) {
			PhotoReport report = response.readEntity(PhotoReport.class);
			return report;
		}
		return null;
	}

	public List<PhotoReport> getListReports(int pageNumber) {
		if (pageNumber <= 0) {
			return null;
		}
		
		int fromIndex = (pageNumber - 1) * PhotoReportServlet.MAX_ENTRIES_PER_PAGE + 1;
		int toIndex = fromIndex + PhotoReportServlet.MAX_ENTRIES_PER_PAGE - 1;
		int maxIndex = getNumberOfReport();
		if (toIndex > maxIndex) {
			toIndex = maxIndex;
		}
		
		Response response = client
				.target(String.format(PATH + "/get?fromIndex=%d&toIndex=%d", fromIndex, toIndex))
				.request(MediaType.APPLICATION_XML)
				.header("Authorization", getAuthorizationString())
				.get();
		
		if (isSuccessful(response.getStatus())) {
			GenericType<List<PhotoReport>> list = new GenericType<List<PhotoReport>>(){};
			List<PhotoReport> listReports = response.readEntity(list);
			return listReports;
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public int getQuantity(int uploadedPhotoId) {
		Response response = client
				.target(String.format(PATH + "/quantity?id=%d", uploadedPhotoId))
				.request(MediaType.APPLICATION_XML)
				.header("Authorization", getAuthorizationString())
				.get();
		if (isSuccessful(response.getStatus())) {
			PrimitiveType result = response.readEntity(PrimitiveType.class);
			return (int) result.getValue();
		}
		return 0;
	}

}
