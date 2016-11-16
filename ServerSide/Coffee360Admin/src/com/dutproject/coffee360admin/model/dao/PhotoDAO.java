package com.dutproject.coffee360admin.model.dao;

public class PhotoDAO extends BaseDAO {
	private static final String PATH = getPath("Coffee360Service/rest/v1/photo");

	public String getPhotoUrl(int uploadedPhotoId) {
		return String.format(PATH + "/%d", uploadedPhotoId);
	}

}
