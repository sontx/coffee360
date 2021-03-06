package com.dutproject.coffee360.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dutproject.coffee360.model.bean.PhotoReport;
import com.dutproject.coffee360.model.bean.PlaceReport;
import com.dutproject.coffee360.model.bean.Report;
import com.dutproject.coffee360.model.bean.ReportState;
import com.dutproject.coffee360.model.dao.provider.IReportProvider;

public class ReportJdbcDAO extends JdbcBaseDAO implements IReportProvider {

	@Override
	public List<PlaceReport> getPlaceReports(int fromIndex, int toIndex) throws SQLException {
		Connection connection = connectionProvider.getConnection();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			String sql = String
					.format("SELECT report.reportId, report.userAccountId, report.message, report.dateTime, report.state, "
							+ "placereport.placeId, "
							+ "place.placeName "
							+ "FROM report INNER JOIN placereport ON report.reportId=placereport.reportId "
							+ "INNER JOIN place ON placereport.placeId=place.placeId "
							+ "LIMIT %d OFFSET %d", fromIndex + toIndex + 1, fromIndex);
			ResultSet resultSet = statement.executeQuery(sql);
			List<PlaceReport> reports = new ArrayList<>();
			while (resultSet.next()) {
				PlaceReport report = new PlaceReport();
				report.setId(resultSet.getInt("reportId"));
				report.setAccountId(resultSet.getInt("userAccountId"));
				report.setCaption(resultSet.getString("message"));
				report.setDateTime(resultSet.getTimestamp("dateTime"));
				report.setState(ReportState.valueOf(resultSet.getString("state")));
				report.setPlaceId(resultSet.getInt("placeId"));
				report.setPalceName(resultSet.getString("placeName"));
				reports.add(report);
			}
			return reports;
		} finally {
			if (statement != null)
				statement.close();
		}
	}

	@Override
	public int getPlacesCount() throws SQLException {
		Connection connection = connectionProvider.getConnection();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			String sql = "SELECT COUNT(*) FROM placereport";
			ResultSet resultSet = statement.executeQuery(sql);
			int placesCount = 0;
			if (resultSet.next())
				placesCount = resultSet.getInt(1);
			return placesCount;
		} finally {
			if (statement != null)
				statement.close();
		}
	}

	@Override
	public Report getPlaceReport(int id) throws SQLException {
		Connection connection = connectionProvider.getConnection();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			String sql = String
					.format("SELECT report.reportId, report.userAccountId, report.message, report.dateTime, report.state, "
							+ "placereport.placeId,"
							+ "place.placeName "
							+ "FROM report INNER JOIN placereport ON report.reportId=placereport.reportId "
							+ "INNER JOIN place ON placereport.placeId=place.placeId "
							+ "WHERE report.reportId=%d", id);
			ResultSet resultSet = statement.executeQuery(sql);
			if (resultSet.next()) { 
				PlaceReport report = new PlaceReport();
				report.setId(resultSet.getInt("reportId"));
				report.setAccountId(resultSet.getInt("userAccountId"));
				report.setCaption(resultSet.getString("message"));
				report.setDateTime(resultSet.getTimestamp("dateTime"));
				report.setState(ReportState.valueOf(resultSet.getString("state")));
				report.setPlaceId(resultSet.getInt("placeId"));
				report.setPalceName(resultSet.getString("placeName"));
				return report;
			}
			return null;
		} finally {
			if (statement != null)
				statement.close();
		}
	}

	@Override
	public int getPlaceQuantity(int id) throws SQLException {
		Connection connection = connectionProvider.getConnection();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			String sql = "SELECT COUNT(*) FROM placereport WHERE placeId=" + id;
			ResultSet resultSet = statement.executeQuery(sql);
			int placesCount = 0;
			if (resultSet.next())
				placesCount = resultSet.getInt(1);
			return placesCount;
		} finally {
			if (statement != null)
				statement.close();
		}
	}

	@Override
	public void setReportState(int id, String state) throws SQLException {
		Connection connection = connectionProvider.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String sql = "UPDATE report SET state=? WHERE reportId=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, state);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
		} finally {
			if (preparedStatement != null)
				preparedStatement.close();
		}
	}

	@Override
	public List<PhotoReport> getPhotoReports(int fromIndex, int toIndex) throws SQLException {
		Connection connection = connectionProvider.getConnection();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			String sql = String
					.format("SELECT report.reportId, report.userAccountId, report.message, report.dateTime, report.state, "
							+ "photoreport.uploadedPhotoId "
							+ "FROM report INNER JOIN photoreport ON report.reportId=photoreport.reportId "
							+ "LIMIT %d OFFSET %d", fromIndex + toIndex + 1, fromIndex);
			ResultSet resultSet = statement.executeQuery(sql);
			List<PhotoReport> reports = new ArrayList<>();
			while (resultSet.next()) {
				PhotoReport report = new PhotoReport();
				report.setId(resultSet.getInt("reportId"));
				report.setAccountId(resultSet.getInt("userAccountId"));
				report.setCaption(resultSet.getString("message"));
				report.setDateTime(resultSet.getTimestamp("dateTime"));
				report.setState(ReportState.valueOf(resultSet.getString("state")));
				report.setUploadedPhotoId(resultSet.getInt("uploadedPhotoId"));
				reports.add(report);
			}
			return reports;
		} finally {
			if (statement != null)
				statement.close();
		}
	}

	@Override
	public Report getPhotoReport(int id) throws SQLException {
		Connection connection = connectionProvider.getConnection();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			String sql = String
					.format("SELECT report.reportId, report.userAccountId, report.message, report.dateTime, report.state, "
							+ "photoreport.uploadedPhotoId "
							+ "FROM report INNER JOIN photoreport ON report.reportId=photoreport.reportId "
							+ "WHERE report.reportId=%d", id);
			ResultSet resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				PhotoReport report = new PhotoReport();
				report.setId(resultSet.getInt("reportId"));
				report.setAccountId(resultSet.getInt("userAccountId"));
				report.setCaption(resultSet.getString("message"));
				report.setDateTime(resultSet.getTimestamp("dateTime"));
				report.setState(ReportState.valueOf(resultSet.getString("state")));
				report.setUploadedPhotoId(resultSet.getInt("uploadedPhotoId"));
				return report;
			}
			return null;
		} finally {
			if (statement != null)
				statement.close();
		}
	}

	
	@Override
	public int getPhotoQuantity(int id) throws SQLException {
		Connection connection = connectionProvider.getConnection();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			String sql = "SELECT COUNT(*) FROM photoreport WHERE uploadedPhotoId=" + id;
			ResultSet resultSet = statement.executeQuery(sql);
			int placesCount = 0;
			if (resultSet.next())
				placesCount = resultSet.getInt(1);
			return placesCount;
		} finally {
			if (statement != null)
				statement.close();
		}
	}

	@Override
	public int getPhotosCount() throws SQLException {
		Connection connection = connectionProvider.getConnection();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			String sql = "SELECT COUNT(*) FROM photoreport";
			ResultSet resultSet = statement.executeQuery(sql);
			int placesCount = 0;
			if (resultSet.next())
				placesCount = resultSet.getInt(1);
			return placesCount;
		} finally {
			if (statement != null)
				statement.close();
		}
	}

}
