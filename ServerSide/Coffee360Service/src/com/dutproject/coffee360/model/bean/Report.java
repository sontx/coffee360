package com.dutproject.coffee360.model.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "report")
public class Report {
	private int id;
	private int accountId;
	private int placeId;
	private String caption;
	private String dateTime;

	public int getId() {
		return id;
	}

	@XmlElement
	public void setId(int id) {
		this.id = id;
	}

	public int getAccountId() {
		return accountId;
	}

	@XmlElement
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getPlaceId() {
		return placeId;
	}

	@XmlElement
	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}

	public String getCaption() {
		return caption;
	}

	@XmlElement
	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getDateTime() {
		return dateTime;
	}

	@XmlElement
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
}
