package com.dutproject.coffee360admin.model.bo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.dutproject.coffee360.model.bean.Tag;
import com.dutproject.coffee360admin.model.dao.TagDAO;
import com.dutproject.coffee360admin.util.Converter;

public class TagBO {
	private TagDAO tagDAO = new TagDAO();

	public List<Tag> getTags(int[] tagIds) {
		if (tagIds == null || tagIds.length <= 0) {
			return null;
		}
		
		List<Tag> tags = new ArrayList<Tag>();
		for (int tagId : tagIds) {
			Tag tag = getTag(tagId);
			tags.add(tag);
		}
		return tags;
	}

	public Tag getTag(int tagId) {
		return tagDAO.getTag(tagId);
	}

	public int[] getIds(String sTags) {
		List<String> tagNames = Converter.split(sTags, Tag.DELIMITER);
		Set<Integer> tagIds = new HashSet<>();
		for (String tagName : tagNames) {
			tagIds.add(getId(tagName));
		}
		return Converter.convertIntegersToIntArray(new ArrayList<>(tagIds));
	}

	public int getId(String tagName) {
		return tagDAO.getId(tagName);
	}
}
