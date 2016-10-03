package com.dutproject.coffee360.model.dao.provider;

import java.util.ArrayList;

import com.dutproject.coffee360.model.bean.Place;
import com.dutproject.coffee360.model.bean.RequestPlace;

public interface IPlaceProvider {
	ArrayList<Place> getPlaces(RequestPlace requestPlace) throws Throwable;
}
