package com.dutproject.coffee360admin.model.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "photoreport")
public class PhotoReport {
	private int reportId;
	private String username;
	private String photoUrl;
	private String status;
	private int quantity;
	
	public PhotoReport() {
	}

	public int getReportId() {
		return reportId;
	}

	@XmlElement
	public void setReportId(int reportId) {
		this.reportId = reportId;
	}

	public String getUsername() {
		return username;
	}

	@XmlElement
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	@XmlElement
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getStatus() {
		return status;
	}

	@XmlElement
	public void setStatus(String status) {
		this.status = status;
	}

	public int getQuantity() {
		return quantity;
	}

	@XmlElement
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
