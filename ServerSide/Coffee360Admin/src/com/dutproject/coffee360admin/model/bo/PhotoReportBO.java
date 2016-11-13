package com.dutproject.coffee360admin.model.bo;

import java.util.List;

import com.dutproject.coffee360admin.model.bean.PhotoReport;
import com.dutproject.coffee360admin.model.dao.PhotoReportDAO;

public class PhotoReportBO {
	private PhotoReportDAO photoReportDAO = new PhotoReportDAO();

	public List<PhotoReport> getListReports(int pageNumber) {
		return photoReportDAO.getListReports(pageNumber);
	}

	public int getNumberOfReport() {
		return photoReportDAO.getNumberOfReport();
	}

	public PhotoReport getReportById(int reportId) {
		return photoReportDAO.getReportById(reportId);
	}

}
