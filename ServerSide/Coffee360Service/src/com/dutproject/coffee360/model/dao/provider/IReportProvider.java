package com.dutproject.coffee360.model.dao.provider;

import java.util.List;

import com.dutproject.coffee360.model.bean.Report;

public interface IReportProvider {
	List<Report> getPlaceReports(int fromIndex, int toIndex);

	int getPlacesCount();
}
