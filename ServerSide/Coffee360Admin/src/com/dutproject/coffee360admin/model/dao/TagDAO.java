package com.dutproject.coffee360admin.model.dao;

import java.util.ArrayList;
import java.util.List;

import com.dutproject.coffee360.model.bean.Tag;

public class TagDAO {

	public List<Tag> getTagsById(int id) {
		List<Tag> tags = new ArrayList<Tag>();
		tags.add(new Tag("aaa"));
		tags.add(new Tag("bbb"));
		return tags;
	}

}
