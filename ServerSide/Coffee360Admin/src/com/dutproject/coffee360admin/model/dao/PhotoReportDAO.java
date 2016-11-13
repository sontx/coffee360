package com.dutproject.coffee360admin.model.dao;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import com.dutproject.coffee360admin.controller.photo.PhotoReportServlet;
import com.dutproject.coffee360admin.model.bean.PhotoReport;

public class PhotoReportDAO {
	private static final String REPORTS = "";
	private static final String REPORT = "";
	private static final String LIST_REPORTS = "";

	public int getNumberOfReport() {
		Client client = ClientBuilder.newClient();
		Response response = client.target(REPORTS)
				.request()
				.header("Authorization", "Bearer " + AdminAccountDAO.getAccessToken())
				.get();
		int reports = response.readEntity(Integer.class);
		return reports;
	}

	public PhotoReport getReportById(int reportId) {
		Client client = ClientBuilder.newClient();
		Response response = client.target(REPORT)
				.request()
				.header("Authorization", "Bearer " + AdminAccountDAO.getAccessToken())
				.get();
		PhotoReport report = response.readEntity(PhotoReport.class);
		return report;
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
		
		Client client = ClientBuilder.newClient();
		Response response = client.target(String.format(LIST_REPORTS, fromIndex, toIndex))
				.request()
				.header("Authorization", "Bearer " + AdminAccountDAO.getAccessToken())
				.get();
		List<PhotoReport> listReports = response.readEntity(new GenericType<List<PhotoReport>>(){});
		return null;
	}

}
