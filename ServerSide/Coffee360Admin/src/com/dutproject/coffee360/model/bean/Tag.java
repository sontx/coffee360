package com.dutproject.coffee360.model.bean;

public class Tag {
	private String name;
	public static final String DELIMITER = ",";
	
	public Tag() {
	}

	public Tag(String name) {
		setName(name);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
