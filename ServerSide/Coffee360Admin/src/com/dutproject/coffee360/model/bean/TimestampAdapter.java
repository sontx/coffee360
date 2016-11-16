package com.dutproject.coffee360.model.bean;

import java.sql.Timestamp;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class TimestampAdapter extends XmlAdapter<Date, Timestamp>{

	@Override
	public Date marshal(Timestamp t) throws Exception {
		return new Date(t.getTime());
	}

	@Override
	public Timestamp unmarshal(Date d) throws Exception {
		return new Timestamp(d.getTime());
	}

}
