package com.dutproject.coffee360.model.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "account")
public class Account {
	public static final int PERMISSION_GUEST = 0;
	public static final int PERMISSION_USER = 1;
	public static final int PERMISSION_ADMIN = 2;

	private int id;
	private int permission;
	private String userName;

	public int getId() {
		return id;
	}

	@XmlElement
	public void setId(int id) {
		this.id = id;
	}

	public int getPermission() {
		return permission;
	}

	@XmlElement
	public void setPermission(int permission) {
		this.permission = permission;
	}

	public static int getPermissionFromString(String st) {
		if ("Admin".equals(st))
			return PERMISSION_ADMIN;
		if ("User".equals(st))
			return PERMISSION_USER;
		return PERMISSION_GUEST;
	}

	public String getUserName() {
		return userName;
	}

	@XmlElement
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
