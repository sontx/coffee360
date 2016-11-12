package com.dutproject.coffee360.model.dao.provider;

import java.sql.SQLException;
import java.util.List;

import com.dutproject.coffee360.model.bean.PlaceReport;
import com.dutproject.coffee360.model.bean.Report;

public interface IReportProvider {
	List<PlaceReport> getPlaceReports(int fromIndex, int toIndex) throws SQLException;

	int getPlacesCount() throws SQLException;

	Report getPlaceReport(int id) throws SQLException;

	int getPlaceQuantity(int id) throws SQLException;
}
