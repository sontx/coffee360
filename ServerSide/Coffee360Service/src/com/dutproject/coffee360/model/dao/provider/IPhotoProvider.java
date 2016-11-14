package com.dutproject.coffee360.model.dao.provider;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import com.dutproject.coffee360.model.bean.UploadedPhoto;

public interface IPhotoProvider {
	UploadedPhoto uploadPlacePhoto(int accountId, InputStream in, String fileName, int placeId) throws IOException, SQLException;
}
