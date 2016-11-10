package com.dutproject.coffee360.model.dao.provider;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import com.dutproject.coffee360.model.bean.Account;
import com.dutproject.coffee360.model.bean.UploadedPhoto;

public interface IPhotoProvider {
	UploadedPhoto uploadPhoto(Account account, InputStream in, String fileName) throws IOException, SQLException;
}
