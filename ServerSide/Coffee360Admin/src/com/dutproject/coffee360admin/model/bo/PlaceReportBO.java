package com.dutproject.coffee360admin.model.bo;

import java.util.ArrayList;
import java.util.List;

import com.dutproject.coffee360.model.bean.Place;
import com.dutproject.coffee360.model.bean.PlaceReport;
import com.dutproject.coffee360admin.controller.place.PlaceReportServlet;
import com.dutproject.coffee360admin.model.bean.PlaceReportDetails;
import com.dutproject.coffee360admin.model.dao.PlaceDAO;
import com.dutproject.coffee360admin.model.dao.PlaceReportDAO;

public class PlaceReportBO {
	private PlaceReportDAO placeReportDAO = new PlaceReportDAO();
	private PlaceDAO placeDAO = new PlaceDAO();

	public int getNumberOfReport() {
		return placeReportDAO.getNumberOfReport();
	}

	public List<PlaceReportDetails> getReportDetails(int pageNumber) {
		if (pageNumber <= 0) {
			return null;
		}
		
		int totalOfCoffeeShopReport = getNumberOfReport();
		int fromIndex = 1 + (pageNumber - 1) * PlaceReportServlet.MAX_ENTRIES_PER_PAGE;
		int toIndex = (fromIndex + PlaceReportServlet.MAX_ENTRIES_PER_PAGE - 1);
		if (toIndex > totalOfCoffeeShopReport) {
			toIndex = totalOfCoffeeShopReport;
		}
		
		List<PlaceReport> listPlaceReports = placeReportDAO.getListReport(fromIndex, toIndex);
		if (null == listPlaceReports || 0 == listPlaceReports.size()) {
			return null;
		}
		
		List<PlaceReportDetails> listPlaceReportDetails = new ArrayList<PlaceReportDetails>();
		for (PlaceReport placeReport : listPlaceReports) {
			PlaceReportDetails reportDetails = getPlaceReportDetailsFromPlaceReport(placeReport);
			listPlaceReportDetails.add(reportDetails);
		}
		return listPlaceReportDetails;
	}

	public PlaceReportDetails getReportDetailsById(int id) {
		PlaceReport placeReport = getReport(id);
		PlaceReportDetails reportDetails = getPlaceReportDetailsFromPlaceReport(placeReport);
		return reportDetails;
	}
	
	public PlaceReport getReport(int reportId) {
		return placeReportDAO.getReport(reportId);
	}

	private PlaceReportDetails getPlaceReportDetailsFromPlaceReport(PlaceReport placeReport) {
		Place place = placeDAO.getPlace(placeReport.getPlaceId());
		int reportQuantity = placeReportDAO.getReportQuantity(placeReport.getPlaceId());
		PlaceReportDetails reportDetails =
				new PlaceReportDetails(placeReport, place, reportQuantity);
		return reportDetails;
	}

}
