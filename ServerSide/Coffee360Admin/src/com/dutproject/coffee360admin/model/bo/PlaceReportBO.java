package com.dutproject.coffee360admin.model.bo;

import java.util.ArrayList;
import java.util.List;

import com.dutproject.coffee360.model.bean.Place;
import com.dutproject.coffee360.model.bean.PlaceReport;
import com.dutproject.coffee360admin.model.bean.PlaceReportDetails;
import com.dutproject.coffee360admin.model.dao.PlaceDAO;
import com.dutproject.coffee360admin.model.dao.PlaceReportDAO;

public class PlaceReportBO {
	private PlaceReportDAO placeReportDAO = new PlaceReportDAO();
	private PlaceDAO placeDAO = new PlaceDAO();

	public int getNumberOfReport() {
		return placeReportDAO.getNumberOfReport();
	}

	public List<PlaceReportDetails> getListReportDetails(int pageNumber) {
		List<PlaceReportDetails> listPlaceReportDetails = new ArrayList<PlaceReportDetails>();
		List<PlaceReport> listPlaceReports = placeReportDAO.getListReport(pageNumber);
		for (PlaceReport placeReport : listPlaceReports) {
			PlaceReportDetails reportDetails = getPlaceReportDetailsFromPlaceReport(placeReport);
			listPlaceReportDetails.add(reportDetails);
		}
		return listPlaceReportDetails;
	}

	public PlaceReportDetails getReportDetailsById(int id) {
		PlaceReport placeReport = placeReportDAO.getReportByReportId(id);
		PlaceReportDetails reportDetails = getPlaceReportDetailsFromPlaceReport(placeReport);
		return reportDetails;
	}
	
	private PlaceReportDetails getPlaceReportDetailsFromPlaceReport(PlaceReport placeReport) {
		Place place = placeDAO.getPlace(placeReport.getPlaceId());
		int reportQuantity = placeReportDAO.getReportQuantity(placeReport.getPlaceId());
		PlaceReportDetails reportDetails =
				new PlaceReportDetails(placeReport, place, reportQuantity);
		return reportDetails;
	}

}
