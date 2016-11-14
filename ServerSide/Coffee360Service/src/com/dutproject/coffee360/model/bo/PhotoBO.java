package com.dutproject.coffee360.model.bo;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Calendar;

import com.dutproject.coffee360.model.bean.UploadedPhoto;
import com.dutproject.coffee360.model.dao.PhotoJdbcDAO;
import com.dutproject.coffee360.model.dao.provider.IPhotoProvider;
import com.dutproject.coffee360.utils.SecuredTokenFactory;

public class PhotoBO {
	private IPhotoProvider photoDAO = new PhotoJdbcDAO();
	
	private String generateUploadFileName(int accountId, String originFileName) {
		Calendar calendar = Calendar.getInstance();
		String randomToken = SecuredTokenFactory.generateSecuredToken();
		String validFileName = originFileName.replace(' ', '-').toLowerCase();
		// yyyy MM dd hh mm ss - random - id - filename
		return String.format("%04d%02d%02d%02d%02d%02d-%s-%013d-%s", 
				calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH) + 1,
				calendar.get(Calendar.DAY_OF_MONTH),
				calendar.get(Calendar.HOUR_OF_DAY),
				calendar.get(Calendar.MINUTE),
				calendar.get(Calendar.SECOND),
				randomToken,
				accountId,
				validFileName);
	}
	
	public UploadedPhoto uploadPlacePhoto(int accountId, InputStream in, String fileName, int placeId) {
		try {
			fileName = generateUploadFileName(accountId, fileName);
			return photoDAO.uploadPlacePhoto(accountId, in, fileName, placeId);
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
