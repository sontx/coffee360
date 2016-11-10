package com.dutproject.coffee360.model.bo;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import com.dutproject.coffee360.model.bean.Account;
import com.dutproject.coffee360.model.bean.UploadedPhoto;
import com.dutproject.coffee360.model.dao.PhotoJdbcDAO;
import com.dutproject.coffee360.model.dao.provider.IPhotoProvider;

public class PhotoBO {
	private IPhotoProvider photoDAO = new PhotoJdbcDAO();
	
	public UploadedPhoto uploadPhoto(Account account, InputStream in, String fileName) {
		try {
			return photoDAO.uploadPhoto(account, in, fileName);
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
