package com.dutproject.coffee360.model.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "useraccount")
public class UserAccount extends Account {
	private String accessToken;
	private String address;
	private String fullName;
	private String gender;
	private int avatarId;

	@XmlTransient
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAddress() {
		return address;
	}

	@XmlElement
	public void setAddress(String address) {
		this.address = address;
	}

	public String getFullName() {
		return fullName;
	}

	@XmlElement
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getGender() {
		return gender;
	}

	@XmlElement
	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAvatarId() {
		return avatarId;
	}

	@XmlElement
	public void setAvatarId(int avatarId) {
		this.avatarId = avatarId;
	}
}
