package com.dutproject.coffee360.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.dutproject.coffee360.model.bean.Address;
import com.dutproject.coffee360.model.bean.Place;
import com.dutproject.coffee360.model.bean.RequestPlace;
import com.dutproject.coffee360.model.dao.jdbc.DatabaseManager;
import com.dutproject.coffee360.model.dao.jdbc.IConnectionProvider;
import com.dutproject.coffee360.model.dao.provider.IPlaceProvider;

public class PlaceJdbcDAO implements IPlaceProvider {
	private IConnectionProvider connectionProvider;

	public PlaceJdbcDAO() {
		connectionProvider = DatabaseManager.getInstance().getConnectionProvider();
	}

	@Override
	public ArrayList<Place> getPlaces(RequestPlace requestPlace) throws Throwable {
		Connection connection = connectionProvider.getConnection();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			String sql = String.format("SELECT place.placeId, place.name AS placeName, place.tag, place.description, place.thumbnailId, address.addressId, address.name AS addressName, address.locationLat, address.locationLng FROM place INNER JOIN address ON place.addressId=address.addressId");
			ResultSet resultSet = statement.executeQuery(sql);
			ArrayList<Place> places = new ArrayList<>();
			while (resultSet.next()) {
				Place place = new Place();
				place.setId(resultSet.getInt("placeId"));
				place.setName(resultSet.getString("placeName"));
				place.setTag(resultSet.getString("tag"));
				place.setDescription(resultSet.getString("description"));
				place.setThumbnailId(resultSet.getInt("thumbnailId"));
				Address address = new Address();
				address.setId(resultSet.getInt("addressId"));
				address.setName(resultSet.getString("addressName"));
				address.setLocationLat(resultSet.getDouble("locationLat"));
				address.setLocationLng(resultSet.getDouble("locationLng"));
				place.setAddress(address);
				places.add(place);
			}
			return places;
		} finally {
			if (statement != null)
				statement.close();
		}
	}

}