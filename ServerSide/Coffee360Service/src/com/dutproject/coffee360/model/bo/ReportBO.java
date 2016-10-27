package com.dutproject.coffee360.model.bo;

import java.util.List;

import com.dutproject.coffee360.model.bean.Report;
import com.dutproject.coffee360.model.dao.ReportTemplateDAO;
import com.dutproject.coffee360.model.dao.provider.IReportProvider;

public class ReportBO {
	private IReportProvider reportProvider = new ReportTemplateDAO();
	
	public List<Report> getPlaceReports(int fromIndex, int toIndex) {
		return reportProvider.getPlaceReports(fromIndex, toIndex);
	}

	public int getPlacesCount() {
		return reportProvider.getPlacesCount();
	}

	public boolean deletePlace(int id) {
		return reportProvider.deletePlace(id);
	}

	public Report getPlaceReport(int id) {
		return reportProvider.getPlaceReport(id);
	}
}
