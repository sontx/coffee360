package com.dutproject.coffee360admin.model.bean;

public class PhotoReportDetails {
	private int reportId;
	private String photoUrl;
	private String caption;
	private int quantity;

	public PhotoReportDetails(int reportId, String photoUrl, String cation,
			int quantity) {
		this.reportId = reportId;
		this.photoUrl = photoUrl;
		this.caption = cation;
		this.quantity = quantity;
	}

	public int getReportId() {
		return reportId;
	}

	public void setReportId(int reportId) {
		this.reportId = reportId;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
