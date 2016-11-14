package com.dutproject.coffee360.model.dao.provider;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dutproject.coffee360.model.bean.Place;
import com.dutproject.coffee360.model.bean.PrimitiveType;
import com.dutproject.coffee360.model.bean.XmlInteger;

public interface IPlaceProvider {
	ArrayList<Place> getPlaces(double locationLat, double locationLng, double radius) throws Throwable;

	Place getPlace(int id) throws Throwable;

	Place addPlace(Place place) throws SQLException;

	boolean deletePlace(int id) throws Throwable;

	Place updatePlace(Place place) throws SQLException;

	ArrayList<XmlInteger> getPlacePhotos(int id) throws SQLException;
}
