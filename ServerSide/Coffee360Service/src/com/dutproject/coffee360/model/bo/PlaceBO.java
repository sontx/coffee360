package com.dutproject.coffee360.model.bo;

import java.util.ArrayList;

import com.dutproject.coffee360.model.bean.Place;
import com.dutproject.coffee360.model.bean.RequestPlace;
import com.dutproject.coffee360.model.dao.PlaceJdbcDAO;
import com.dutproject.coffee360.model.dao.PlaceTemplateDAO;
import com.dutproject.coffee360.model.dao.provider.IPlaceProvider;

public class PlaceBO {
	private IPlaceProvider placeDAO = new PlaceJdbcDAO();
	
	public ArrayList<Place>  getPlaces(RequestPlace requestPlace) {
		try {
			return placeDAO.getPlaces(requestPlace);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
