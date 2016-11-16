package com.dutproject.coffee360.model.bean;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "report")
public class Report {
	private int id;
	private int accountId;
	private String caption;
	private Timestamp dateTime;
	private ReportState state;
	
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

	public String getCaption() {
		return caption;
	}

	@XmlElement
	public void setCaption(String caption) {
		this.caption = caption;
	}

	public Timestamp getDateTime() {
		return dateTime;
	}
	
	@XmlJavaTypeAdapter(TimestampAdapter.class)
	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}

	public ReportState getState() {
		return state;
	}

	public void setState(ReportState state) {
		this.state = state;
	}
}
