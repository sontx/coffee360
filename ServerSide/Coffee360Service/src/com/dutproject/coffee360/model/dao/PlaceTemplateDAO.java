package com.dutproject.coffee360.model.dao;

import java.util.ArrayList;

import com.dutproject.coffee360.model.bean.Place;
import com.dutproject.coffee360.model.bean.RequestPlace;
import com.dutproject.coffee360.model.dao.provider.IPlaceProvider;

public class PlaceTemplateDAO implements IPlaceProvider {

	@Override
	public ArrayList<Place> getPlaces(RequestPlace requestPlace) {
		return Template.getPlaces();
	}

}
