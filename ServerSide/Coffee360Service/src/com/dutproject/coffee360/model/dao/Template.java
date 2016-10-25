package com.dutproject.coffee360.model.dao;

import java.util.ArrayList;
import java.util.List;

import com.dutproject.coffee360.model.bean.Place;
import com.dutproject.coffee360.model.bean.Report;

public class Template {
	public static ArrayList<Place> getPlaces() {
		ArrayList<Place> places = new ArrayList<>();

		Place place = new Place();
		place.setName("place 1");
		places.add(place);

		place = new Place();
		place.setName("place 2");
		places.add(place);

		return places;
	}

	public static List<Report> getPlaceReports() {
		List<Report> reports = new ArrayList<Report>();
		
		Report report = new Report();
		report.setId(1);
		report.setAccountId(1);
		report.setCaption("report 1");
		report.setDateTime("3/3/2016");
		report.setPlaceId(1);
		reports.add(report);
		
		report = new Report();
		report.setId(2);
		report.setAccountId(2);
		report.setCaption("report 2");
		report.setDateTime("3/4/2016");
		report.setPlaceId(2);
		reports.add(report);
		
		report = new Report();
		report.setId(3);
		report.setAccountId(3);
		report.setCaption("report 3");
		report.setDateTime("3/5/2016");
		report.setPlaceId(3);
		reports.add(report);
		
		report = new Report();
		report.setId(4);
		report.setAccountId(4);
		report.setCaption("report 4");
		report.setDateTime("3/6/2016");
		report.setPlaceId(4);
		reports.add(report);
		
		return reports;
	}
}
