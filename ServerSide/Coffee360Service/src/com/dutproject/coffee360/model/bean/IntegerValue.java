package com.dutproject.coffee360.model.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "integer")
public class IntegerValue {
	private int value;

	public int getValue() {
		return value;
	}
	@XmlElement
	public void setValue(int value) {
		this.value = value;
	}
}
