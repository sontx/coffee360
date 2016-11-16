package com.dutproject.coffee360.model.bean;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "place")
public class Place {
	private int id;
	private String name;
	private Address address;
	private int[] tagIds;
	private String description;
	private int thumbnailId;
	
	public Place(int id, String name, Address address, int[] tagIds, String description, int thumbnailId) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.tagIds = tagIds;
		this.description = description;
		this.thumbnailId = thumbnailId;
	}

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

	public int[] getTagIds() {
		return tagIds;
	}

	@XmlElement
	public void setTagIds(int[] tagIds) {
		this.tagIds = tagIds;
	}
}
