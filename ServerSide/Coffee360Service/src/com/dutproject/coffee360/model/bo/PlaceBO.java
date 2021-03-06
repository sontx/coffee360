package com.dutproject.coffee360.model.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dutproject.coffee360.model.bean.Place;
import com.dutproject.coffee360.model.bean.PrimitiveType;
import com.dutproject.coffee360.model.bean.Tag;
import com.dutproject.coffee360.model.bean.XmlInteger;
import com.dutproject.coffee360.model.dao.PlaceJdbcDAO;
import com.dutproject.coffee360.model.dao.provider.IPlaceProvider;

public class PlaceBO {
	private IPlaceProvider placeDAO = new PlaceJdbcDAO();
	
	public ArrayList<Place>  getPlaces(double locationLat, double locationLng, double radius) {
		try {
			return placeDAO.getPlaces(locationLat, locationLng, radius);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Place getPlace(int id) {
		try {
			return placeDAO.getPlace(id);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Place addPlace(int accountId, Place place) {
		try {
			place.setOwnerId(accountId);
			place.setCreatorId(accountId);
			return placeDAO.addPlace(place);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean deletePlace(int id) {
		try {
			return placeDAO.deletePlace(id);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public Place updatePlace(Place place) throws SQLException {
		return placeDAO.updatePlace(place);
	}

	public ArrayList<XmlInteger> getPlacePhotos(int id) throws SQLException {
		return placeDAO.getPlacePhotos(id);
	}

	public Tag getTagById(int id) throws SQLException {
		return placeDAO.getTagById(id);
	}

	public Tag getTagByName(String name) throws SQLException {
		return placeDAO.getTagByName(name);
	}

}
