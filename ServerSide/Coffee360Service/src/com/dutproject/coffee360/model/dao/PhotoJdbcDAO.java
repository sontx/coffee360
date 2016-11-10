package com.dutproject.coffee360.model.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;

import com.dutproject.coffee360.model.bean.Account;
import com.dutproject.coffee360.model.bean.UploadedPhoto;
import com.dutproject.coffee360.model.dao.provider.IPhotoProvider;

public class PhotoJdbcDAO extends JdbcBaseDAO implements IPhotoProvider {

	@Override
	public UploadedPhoto uploadPhoto(Account account, InputStream in, String fileName)
			throws IOException, SQLException {
		String dataUrl = ResourceManager.getInstance().writePhoto(in, fileName).getPath();
		Calendar calendar = Calendar.getInstance();
		Timestamp now = new Timestamp(calendar.getTimeInMillis());

		String sql = "INSERT INTO uploadedphoto(dataUrl, dateTime, userAccountId) VALUES(?,?,?)";
		Connection connection = connectionProvider.getConnection();
		PreparedStatement prepareStatement = null;
		Statement statement = null;
		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, dataUrl);
			prepareStatement.setObject(2, now);
			prepareStatement.setInt(3, account.getId());
			prepareStatement.executeUpdate();

			statement = connection.createStatement();
			int uploadedPhotoId = getLastRowId(statement, "uploadedphoto", "uploadedPhotoId");

			UploadedPhoto uploadedPhoto = new UploadedPhoto();
			uploadedPhoto.setDataUrl(dataUrl);
			uploadedPhoto.setDateTime(now);
			uploadedPhoto.setId(uploadedPhotoId);
			uploadedPhoto.setUserAccountId(account.getId());
			return uploadedPhoto;
		} finally {
			if (prepareStatement != null)
				prepareStatement.close();
			if (statement != null)
				statement.close();
		}
	}

}
