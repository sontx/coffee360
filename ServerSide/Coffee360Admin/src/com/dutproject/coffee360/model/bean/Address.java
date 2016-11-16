package com.dutproject.coffee360.model.bean;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "address")
public class Address {
	private int id;
	private String name;
	private double locationLat;
	private double locationLng;

	public Address(int id, String name, double locationLat, double locationLng) {
		this.id = id;
		this.name = name;
		this.locationLat = locationLat;
		this.locationLng = locationLng;
	}
	
	public String getName() {
		return name;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	public double getLocationLat() {
		return locationLat;
	}

	@XmlElement
	public void setLocationLat(double locationLat) {
		this.locationLat = locationLat;
	}

	public double getLocationLng() {
		return locationLng;
	}

	@XmlElement
	public void setLocationLng(double locationLng) {
		this.locationLng = locationLng;
	}

	public int getId() {
		return id;
	}

	@XmlElement
	public void setId(int id) {
		this.id = id;
	}
}
