package com.dutproject.coffee360.model.dao;

import java.util.ArrayList;

import com.dutproject.coffee360.model.bean.Place;

public class Template {
	public static ArrayList<Place> getPlaces(){
		ArrayList<Place> places = new ArrayList<>();
		
		Place place = new Place();
		place.setName("place 1");
		places.add(place);
		
		place = new Place();
		place.setName("place 2");
		places.add(place);
		
		return places;
	}
}
