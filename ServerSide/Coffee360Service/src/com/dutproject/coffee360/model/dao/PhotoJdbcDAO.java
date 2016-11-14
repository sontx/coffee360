package com.dutproject.coffee360.model.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;

import com.dutproject.coffee360.model.bean.UploadedPhoto;
import com.dutproject.coffee360.model.dao.provider.IPhotoProvider;

public class PhotoJdbcDAO extends JdbcBaseDAO implements IPhotoProvider {

	@Override
	public UploadedPhoto uploadPlacePhoto(int accountId, InputStream in, String fileName, int placeId)
			throws IOException, SQLException {
		String dataUrl = ResourceManager.getInstance().writePhoto(in, fileName).getPath();
		Calendar calendar = Calendar.getInstance();
		Timestamp now = new Timestamp(calendar.getTimeInMillis());

		String sql = "INSERT INTO uploadedphoto(dataUrl, dateTime, accountId) VALUES(?,?,?)";
		Connection connection = connectionProvider.getConnection();
		PreparedStatement prepareStatement = null;
		Statement statement = null;
		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, dataUrl);
			prepareStatement.setObject(2, now);
			prepareStatement.setInt(3, accountId);
			prepareStatement.executeUpdate();
			prepareStatement.close();

			statement = connection.createStatement();
			int uploadedPhotoId = getLastRowId(statement, "uploadedphoto", "uploadedPhotoId");

			UploadedPhoto uploadedPhoto = new UploadedPhoto();
			uploadedPhoto.setDataUrl(dataUrl);
			uploadedPhoto.setDateTime(now);
			uploadedPhoto.setId(uploadedPhotoId);
			uploadedPhoto.setUserAccountId(accountId);
			
			sql = "INSERT INTO placephoto(placeId, uploadedPhotoId) VALUES(?,?)";
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, placeId);
			prepareStatement.setInt(2, uploadedPhotoId);
			prepareStatement.executeUpdate();
			
			return uploadedPhoto;
		} finally {
			if (prepareStatement != null)
				prepareStatement.close();
			if (statement != null)
				statement.close();
		}
	}

}
