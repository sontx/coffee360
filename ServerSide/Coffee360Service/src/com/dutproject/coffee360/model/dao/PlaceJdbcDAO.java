package com.dutproject.coffee360.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dutproject.coffee360.model.bean.Address;
import com.dutproject.coffee360.model.bean.Place;
import com.dutproject.coffee360.model.dao.jdbc.DatabaseManager;
import com.dutproject.coffee360.model.dao.jdbc.IConnectionProvider;
import com.dutproject.coffee360.model.dao.provider.IPlaceProvider;

public class PlaceJdbcDAO implements IPlaceProvider {
	private IConnectionProvider connectionProvider;

	public PlaceJdbcDAO() {
		connectionProvider = DatabaseManager.getInstance().getConnectionProvider();
	}

	@Override
	public ArrayList<Place> getPlaces(double locationLat, double locationLng, double radius) throws Throwable {
		Connection connection = connectionProvider.getConnection();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			double latMin = locationLat - radius;
			double latMax = locationLat + radius;
			double lngMin = locationLng - radius;
			double lngMax = locationLng + radius;
			String sql = String.format(
					"SELECT place.placeId, place.placeName, place.description, place.thumbnailId, " +
				    "address.addressId, address.addressName, "+
					"googlemapscoordinates.locationLatitude, googlemapscoordinates.locationLongitude " +
				    "FROM place INNER JOIN address ON place.addressId=address.addressId " +
					"INNER JOIN googlemapscoordinates ON address.googleMapsCoordinatesId=googlemapscoordinates.googleMapsCoordinatesId " +
				    "WHERE (googlemapscoordinates.locationLatitude>=%f AND googlemapscoordinates.locationLatitude<=%f) AND (googlemapscoordinates.locationLongitude>=%f AND googlemapscoordinates.locationLongitude<=%f)", 
					latMin, latMax, lngMin, lngMax);
			ResultSet resultSet = statement.executeQuery(sql);
			ArrayList<Place> places = new ArrayList<>();
			while (resultSet.next()) {
				Place place = new Place();
				place.setId(resultSet.getInt("placeId"));
				place.setName(resultSet.getString("placeName"));
				place.setDescription(resultSet.getString("description"));
				place.setThumbnailId(resultSet.getInt("thumbnailId"));
				
				Address address = new Address();
				address.setId(resultSet.getInt("addressId"));
				address.setName(resultSet.getString("addressName"));
				address.setLocationLat(resultSet.getDouble("locationLatitude"));
				address.setLocationLng(resultSet.getDouble("locationLongitude"));
				place.setAddress(address);
				
				sql = String.format("SELECT tagId FROM placetag WHERE placeId=%d", place.getId());
				ResultSet tagsResultSet = statement.executeQuery(sql);
				List<Integer> tagIds = new ArrayList<>();
				while (tagsResultSet.next()) {
					tagIds.add(tagsResultSet.getInt("tagId"));
				}
				tagsResultSet.close();
				int[] tagIdsArray = new int[tagIds.size()];
				for (int i = 0; i < tagIdsArray.length; i++) {
					tagIdsArray[i] = tagIds.get(i);
				}
				place.setTagIds(tagIdsArray);
				
				places.add(place);
			}
			return places;
		} finally {
			if (statement != null)
				statement.close();
		}
	}

	@Override
	public Place getPlace(int id) throws Throwable {
		Connection connection = connectionProvider.getConnection();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			String sql = String.format(
					"SELECT place.placeId, place.placeName, place.description, place.thumbnailId, " +
					"address.addressId, address.addressName, "+
					"googlemapscoordinates.locationLatitude, googlemapscoordinates.locationLongitude " +
					"FROM place INNER JOIN address ON place.addressId=address.addressId " +
					"INNER JOIN googlemapscoordinates ON address.googleMapsCoordinatesId=googlemapscoordinates.googleMapsCoordinatesId " +
					"WHERE place.placeId=%d", 
					id);
			ResultSet resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				Place place = new Place();
				place.setId(resultSet.getInt("placeId"));
				place.setName(resultSet.getString("placeName"));
				place.setDescription(resultSet.getString("description"));
				place.setThumbnailId(resultSet.getInt("thumbnailId"));
				
				Address address = new Address();
				address.setId(resultSet.getInt("addressId"));
				address.setName(resultSet.getString("addressName"));
				address.setLocationLat(resultSet.getDouble("locationLatitude"));
				address.setLocationLng(resultSet.getDouble("locationLongitude"));
				place.setAddress(address);
				
				sql = String.format("SELECT tagId FROM placetag WHERE placeId=%d", place.getId());
				ResultSet tagsResultSet = statement.executeQuery(sql);
				List<Integer> tagIds = new ArrayList<>();
				while (tagsResultSet.next()) {
					tagIds.add(tagsResultSet.getInt("tagId"));
				}
				tagsResultSet.close();
				int[] tagIdsArray = new int[tagIds.size()];
				for (int i = 0; i < tagIdsArray.length; i++) {
					tagIdsArray[i] = tagIds.get(i);
				}
				place.setTagIds(tagIdsArray);
				
				return place;
			}
		} finally {
			if (statement != null)
				statement.close();
		}

		return null;
	}

	private int getLastRowId(Statement statement, String tableName, String idColumnName) throws SQLException {
		String sql = String.format("SELECT * FROM %s ORDER BY %s DESC LIMIT 1", tableName, idColumnName);
		ResultSet rs = statement.executeQuery(sql);
		int id = -1;
		if (rs.next())
			id = rs.getInt(idColumnName);
		rs.close();
		return id;
	}

	@Override
	public synchronized Place addPlace(Place place) throws SQLException {
		Connection connection = connectionProvider.getConnection();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			Address address = place.getAddress();
			final int thumbnailId = 0;
			// insert google map coordinates of address
			String sql = String.format(
					"INSERT INTO googlemapscoordinates(locationLatitude, locationLongitude) VALUES(%f, %f)",
					address.getLocationLat(), address.getLocationLng());
			statement.executeUpdate(sql);
			// insert address
			int googleMapsCoordinatesId = getLastRowId(statement, "googlemapscoordinates", "googleMapsCoordinatesId");
			sql = "INSERT INTO address(addressName, googleMapsCoordinatesId) VALUES(?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, address.getName());
			preparedStatement.setInt(2, googleMapsCoordinatesId);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			// insert place
			int addressId = getLastRowId(statement, "address", "addressId");
			sql = "INSERT INTO place(addressId, description, placeName, thumbnailId) VALUES(?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, addressId);
			preparedStatement.setString(2, place.getDescription());
			preparedStatement.setString(3, place.getName());
			preparedStatement.setInt(4, thumbnailId);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			int placeId = getLastRowId(statement, "place", "placeId");
			// insert tag
			sql = "INSERT INTO placetag(placeId, tagId) VALUES(?,?)";
			preparedStatement = connection.prepareStatement(sql);
			for (int tagId : place.getTagIds()) {
				preparedStatement.setInt(1, placeId);
				preparedStatement.setInt(2, tagId);
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
			preparedStatement.close();
			// set return information
			address.setId(addressId);
			place.setId(placeId);
			place.setThumbnailId(thumbnailId);
			return place;
		} finally {
			if (statement != null)
				statement.close();
		}
	}

	@Override
	public boolean deletePlace(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
