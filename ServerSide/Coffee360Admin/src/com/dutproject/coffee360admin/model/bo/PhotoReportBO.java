package com.dutproject.coffee360admin.model.bo;

import java.util.ArrayList;
import java.util.List;

import com.dutproject.coffee360.model.bean.PhotoReport;
import com.dutproject.coffee360admin.model.bean.PhotoReportDetails;
import com.dutproject.coffee360admin.model.dao.PhotoDAO;
import com.dutproject.coffee360admin.model.dao.PhotoReportDAO;

public class PhotoReportBO {
	private PhotoReportDAO photoReportDAO = new PhotoReportDAO();
	private PhotoDAO photoDAO = new PhotoDAO();

	public List<PhotoReportDetails> getListReports(int pageNumber) {
		List<PhotoReport> listReports = photoReportDAO.getListReports(pageNumber);
		List<PhotoReportDetails> listReportDetails = new ArrayList<PhotoReportDetails>();
		for (PhotoReport report : listReports) {
			listReportDetails.add(getReportDetails(report));
		}
		return listReportDetails;
	}

	public int getNumberOfReport() {
		return photoReportDAO.getNumberOfReport();
	}

	public PhotoReportDetails getReportById(int reportId) {
		PhotoReport report = photoReportDAO.getReportById(reportId);
		return getReportDetails(report);
	}
	
	private PhotoReportDetails getReportDetails(PhotoReport report) {
		int reportId = report.getId();
		String photoUrl = photoDAO.getPhotoUrl(report.getUploadedPhotoId());
		String caption = report.getCaption();
		int quantity = photoReportDAO.getQuantity(report.getUploadedPhotoId());
		return new PhotoReportDetails(reportId, photoUrl, caption, quantity);
	}

}
