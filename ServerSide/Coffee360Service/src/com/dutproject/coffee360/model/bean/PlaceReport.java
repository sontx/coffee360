package com.dutproject.coffee360.model.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "placeReport")
public class PlaceReport extends Report {
	private int placeId;

	public int getPlaceId() {
		return placeId;
	}

	@XmlElement
	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}
}
