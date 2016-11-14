package com.dutproject.coffee360.model.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.dutproject.coffee360.config.Constants;

public final class ResourceManager {
	private static ResourceManager instance = null;
	private final File photoResourceDir;
	
	public static ResourceManager getInstance() {
		if (instance == null)
			instance = new ResourceManager();
		return instance;
	}
	
	private File writeToFile(InputStream in, File resourceDir, String fileName) throws IOException {
		File uploadedFileLocation = new File(resourceDir, fileName);
		OutputStream out = null;
		try {
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(uploadedFileLocation);
			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			return uploadedFileLocation;
		} finally {
			if (out != null)
				out.close();
		}
	}
	
	public File writePhoto(InputStream in,String fileName) throws IOException {
		return writeToFile(in, photoResourceDir, fileName);
	}
	
	public InputStream getPhotoInputStream(String baseFileName) throws FileNotFoundException {
		File file = new File(baseFileName);
		return new FileInputStream(file);
	}
	
	private ResourceManager() {
		String resouceDir = System.getProperty(Constants.PROP_KEY_RESOURCE_DIR);
		String photoDirName = System.getProperty(Constants.PROP_KEY_RESOURCE_PHOTO_DIR_NAME);
		photoResourceDir = new File(resouceDir, photoDirName);
		if (!photoResourceDir.isDirectory())
			photoResourceDir.mkdirs();
	}
}
