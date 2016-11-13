package com.dutproject.coffee360admin.model.bo;

import java.util.List;

import com.dutproject.coffee360.model.bean.Tag;
import com.dutproject.coffee360admin.model.dao.TagDAO;

public class TagBO {
	private TagDAO tagDAO = new TagDAO();

	public List<Tag> getTagsById(int id) {
		return tagDAO.getTagsById(id);
	}
}
