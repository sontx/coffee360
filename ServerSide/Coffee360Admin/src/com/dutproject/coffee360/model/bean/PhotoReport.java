package com.dutproject.coffee360.model.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "phototReport")
public class PhotoReport extends Report {
	private int uploadedPhotoId;
	
	public PhotoReport(Report report, int uploadedPhotoId) {
		super(report.getId(), report.getAccountId(), report.getCaption(),
				report.getDateTime(), report.getState());
		this.uploadedPhotoId = uploadedPhotoId;
	}
	
	public int getUploadedPhotoId() {
		return uploadedPhotoId;
	}

	@XmlElement
	public void setUploadedPhotoId(int uploadedPhotoId) {
		this.uploadedPhotoId = uploadedPhotoId;
	}
}
