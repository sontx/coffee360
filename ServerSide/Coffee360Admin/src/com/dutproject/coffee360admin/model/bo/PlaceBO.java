package com.dutproject.coffee360admin.model.bo;

import com.dutproject.coffee360.model.bean.Place;
import com.dutproject.coffee360admin.model.dao.PlaceDAO;

public class PlaceBO {
	private PlaceDAO placeDAO = new PlaceDAO();

	public Place getPlace(int placeId) {
		return placeDAO.getPlace(placeId);
	}

	public boolean update(Place place) {
		return placeDAO.update(place);
	}

}
