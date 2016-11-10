package com.dutproject.coffee360.model.bean;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "uploadedphoto")
public class UploadedPhoto {
	private int id;
	private String dataUrl;
	private Timestamp dateTime;
	private int userAccountId;

	public int getId() {
		return id;
	}

	@XmlElement
	public void setId(int id) {
		this.id = id;
	}

	public String getDataUrl() {
		return dataUrl;
	}

	@XmlElement
	public void setDataUrl(String dataUrl) {
		this.dataUrl = dataUrl;
	}

	public int getUserAccountId() {
		return userAccountId;
	}

	@XmlElement
	public void setUserAccountId(int userAccountId) {
		this.userAccountId = userAccountId;
	}

	public Timestamp getDateTime() {
		return dateTime;
	}

	@XmlElement
	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}

}
