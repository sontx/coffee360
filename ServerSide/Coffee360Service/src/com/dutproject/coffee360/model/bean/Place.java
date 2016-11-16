package com.dutproject.coffee360.model.bean;

import java.sql.Timestamp;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "place")
public class Place {
	private int id;
	private String name;
	private Address address;
	private int[] tagIds;
	private String description;
	private int thumbnailId;
	private int creatorId;
	private int ownerId;
	private Timestamp createdTime;

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

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	@XmlElement
	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public int getOwnerId() {
		return ownerId;
	}

	@XmlElement
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public int getCreatorId() {
		return creatorId;
	}

	@XmlElement
	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}
}
