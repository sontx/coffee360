package com.dutproject.coffee360admin.model.bean;

import java.util.List;

import com.dutproject.coffee360.model.bean.Place;
import com.dutproject.coffee360.model.bean.Tag;

public class PlaceDetails {
	private int id;
	private String name;
	private String description;
	private List<Tag> tags;

	public PlaceDetails(Place place, List<Tag> tags) {
		id = place.getId();
		name = place.getName();
		description = place.getDescription();
		this.tags = tags;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

}
