package com.dutproject.coffee360.model.bean;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "place")
public class Place {
	private int id;
	private String name;
	private Address address;
	private String tag;
	private String description;
	private int thumbnailId;

	public int getId() {
		return id;
	}
	@XmlElement
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}
	@XmlElement
	public void setAddress(Address address) {
		this.address = address;
	}

	public String getTag() {
		return tag;
	}
	@XmlElement
	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getDescription() {
		return description;
	}
	@XmlElement
	public void setDescription(String description) {
		this.description = description;
	}

	public int getThumbnailId() {
		return thumbnailId;
	}
	@XmlElement
	public void setThumbnailId(int thumbnailId) {
		this.thumbnailId = thumbnailId;
	}
}
