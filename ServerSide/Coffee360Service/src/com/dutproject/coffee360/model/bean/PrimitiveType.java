package com.dutproject.coffee360.model.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "primitive")
public class PrimitiveType<T> {
	private T value;

	public T getValue() {
		return value;
	}

	@XmlElement
	public void setValue(T value) {
		this.value = value;
	}
}
