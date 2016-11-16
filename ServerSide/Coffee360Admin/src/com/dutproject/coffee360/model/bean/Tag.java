package com.dutproject.coffee360.model.bean;

public class Tag {
	private int id;
	private String name;
	public static final String DELIMITER = ",";

	public Tag(int id, String name) {
		this.id = id;
		this.name = name;
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

}
