package com.dutproject.coffee360.model.dao;

import java.util.List;

import com.dutproject.coffee360.model.bean.Report;
import com.dutproject.coffee360.model.dao.provider.IReportProvider;

public class ReportTemplateDAO implements IReportProvider {

	@Override
	public List<Report> getPlaceReports(int fromIndex, int toIndex) {
		return Template.getPlaceReports();
	}

	@Override
	public int getPlacesCount() {
		return Template.getPlaceReports().size();
	}

}
