package com.dutproject.coffee360admin.model.bean;

import com.dutproject.coffee360.model.bean.Place;
import com.dutproject.coffee360.model.bean.PlaceReport;

public class PlaceReportDetails {
	private int reportId;
	private String placeName;
	private String description;
	private int quantity;

	public PlaceReportDetails(PlaceReport placeReport, Place place, int reportQuantity) {
		reportId = placeReport.getId();
		placeName = place.getName();
		description = place.getDescription();
		quantity = reportQuantity;
	}

	public int getReportId() {
		return reportId;
	}

	public void setReportId(int id) {
		this.reportId = id;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
