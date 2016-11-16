package com.dutproject.coffee360admin.model.bo;

import java.util.ArrayList;
import java.util.List;

import com.dutproject.coffee360.model.bean.PhotoReport;
import com.dutproject.coffee360admin.controller.photo.PhotoReportServlet;
import com.dutproject.coffee360admin.model.bean.PhotoReportDetails;
import com.dutproject.coffee360admin.model.dao.PhotoDAO;
import com.dutproject.coffee360admin.model.dao.PhotoReportDAO;

public class PhotoReportBO {
	private PhotoReportDAO photoReportDAO = new PhotoReportDAO();
	private PhotoDAO photoDAO = new PhotoDAO();

	public List<PhotoReportDetails> getListReports(int pageNumber) {
		if (pageNumber <= 0) {
			return null;
		}

		int fromIndex = (pageNumber - 1) * PhotoReportServlet.MAX_ENTRIES_PER_PAGE + 1;
		int toIndex = fromIndex + PhotoReportServlet.MAX_ENTRIES_PER_PAGE - 1;
		int maxIndex = getCountReport();
		if (toIndex > maxIndex) {
			toIndex = maxIndex;
		}
		
		List<PhotoReport> reports = photoReportDAO.getListReports(fromIndex, toIndex);
		List<PhotoReportDetails> details = new ArrayList<PhotoReportDetails>();
		for (PhotoReport report : reports) {
			details.add(getReportDetails(report));
		}
		return details;
	}

	public int getCountReport() {
		return photoReportDAO.getCountReport();
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
