package com.dutproject.coffee360.model.dao;

import java.util.ArrayList;

import com.dutproject.coffee360.model.bean.Place;
import com.dutproject.coffee360.model.dao.provider.IPlaceProvider;

public class PlaceTemplateDAO implements IPlaceProvider {

	@Override
	public ArrayList<Place> getPlaces(double locationLat, double locationLng, double radius) {
		return Template.getPlaces();
	}

	@Override
	public Place getPlace(int id) {
		ArrayList<Place> places = Template.getPlaces();
		for (Place place : places) {
			if (place.getId() == id)
				return place;
		}
		return null;
	}

	@Override
	public Place addPlace(Place place) {
		return place;
	}

	@Override
	public boolean deletePlace(int id) {
		// TODO Auto-generated method stub
		return true;
	}

}
