package com.dutproject.coffee360admin.model.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "primitive")
public class NumberOfReport {
	private int value;

	public int getValue() {
		return value;
	}

	@XmlElement
	public void setValue(int value) {
		this.value = value;
	}

}
