package com.dutproject.coffee360.model.bean;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "requestPlace")
public class RequestPlace {
	private Address address;
	private double radius;

	public Address getAddress() {
		return address;
	}

	@XmlElement
	public void setAddress(Address address) {
		this.address = address;
	}

	public double getRadius() {
		return radius;
	}

	@XmlElement
	public void setRadius(double radius) {
		this.radius = radius;
	}
}
