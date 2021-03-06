package com.dutproject.coffee360.model.bo;

import java.sql.SQLException;
import java.util.List;

import com.dutproject.coffee360.model.bean.PhotoReport;
import com.dutproject.coffee360.model.bean.PlaceReport;
import com.dutproject.coffee360.model.bean.Report;
import com.dutproject.coffee360.model.dao.ReportJdbcDAO;
import com.dutproject.coffee360.model.dao.provider.IReportProvider;

public class ReportBO {
	private IReportProvider reportProvider = new ReportJdbcDAO();

	public List<PlaceReport> getPlaceReports(int fromIndex, int toIndex) throws SQLException {
		return reportProvider.getPlaceReports(fromIndex, toIndex);
	}

	public int getPlacesCount() throws SQLException {
		return reportProvider.getPlacesCount();
	}
	
	public Report getPlaceReport(int id) throws SQLException {
		return reportProvider.getPlaceReport(id);
	}

	public int getPlaceQuantity(int id) throws SQLException {
		return reportProvider.getPlaceQuantity(id);
	}

	public void setReportState(int id, String state) throws SQLException {
		reportProvider.setReportState(id, state);		
	}

	public List<PhotoReport> getPhotoReports(int fromIndex, int toIndex) throws SQLException {
		return reportProvider.getPhotoReports(fromIndex, toIndex);
	}

	public Report getPhotoReport(int id) throws SQLException {
		return reportProvider.getPhotoReport(id);
	}

	public int getPhotoQuantity(int id) throws SQLException {
		return reportProvider.getPhotoQuantity(id);
	}

	public int getPhotosCount() throws SQLException {
		return reportProvider.getPhotosCount();
	}

}
