package com.dutproject.coffee360.model.dao.provider;

import java.sql.SQLException;
import java.util.ArrayList;

import com.dutproject.coffee360.model.bean.Place;

public interface IPlaceProvider {
	ArrayList<Place> getPlaces(double locationLat, double locationLng, double radius) throws Throwable;

	Place getPlace(int id) throws Throwable;

	Place addPlace(Place place) throws SQLException;

	boolean deletePlace(int id);
}
