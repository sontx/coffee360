package com.dutproject.coffee360.model.bo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Calendar;

import com.dutproject.coffee360.model.bean.Place;
import com.dutproject.coffee360.model.bean.UploadedPhoto;
import com.dutproject.coffee360.model.dao.PhotoJdbcDAO;
import com.dutproject.coffee360.model.dao.PlaceJdbcDAO;
import com.dutproject.coffee360.model.dao.ResourceManager;
import com.dutproject.coffee360.model.dao.provider.IPhotoProvider;
import com.dutproject.coffee360.model.dao.provider.IPlaceProvider;
import com.dutproject.coffee360.utils.SecuredTokenFactory;

public class PhotoBO {
	private IPhotoProvider photoDAO = new PhotoJdbcDAO();
	private IPlaceProvider placeDAO =new PlaceJdbcDAO();
	
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
	
	public UploadedPhoto uploadPlacePhoto(int accountId, InputStream in, String fileName, int placeId) throws Throwable {
		try {
			fileName = generateUploadFileName(accountId, fileName);
			UploadedPhoto uploadedPhoto = photoDAO.uploadPlacePhoto(accountId, in, fileName, placeId);
			Place place = placeDAO.getPlace(placeId);
			if (place != null && place.getThumbnailId() < 0) {
				place.setThumbnailId(uploadedPhoto.getId());
				placeDAO.updatePlace(place);
			}
			return uploadedPhoto;
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public InputStream getImageInputStream(int id) throws SQLException {
		String dataUrl = photoDAO.getImageDataUrlById(id);
		try {
			return ResourceManager.getInstance().getPhotoInputStream(dataUrl);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

}
