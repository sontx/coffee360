package com.dutproject.coffee360.model.bo;

import java.util.ArrayList;

import com.dutproject.coffee360.model.bean.Place;
import com.dutproject.coffee360.model.dao.PlaceJdbcDAO;
import com.dutproject.coffee360.model.dao.PlaceTemplateDAO;
import com.dutproject.coffee360.model.dao.provider.IPlaceProvider;

public class PlaceBO {
	private IPlaceProvider placeDAO = new PlaceTemplateDAO();
	
	public ArrayList<Place>  getPlaces(double locationLat, double locationLng, double radius) {
		try {
			return placeDAO.getPlaces(locationLat, locationLng, radius);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
