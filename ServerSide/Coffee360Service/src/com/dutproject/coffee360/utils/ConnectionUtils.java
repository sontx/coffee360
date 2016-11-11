package com.dutproject.coffee360.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public final class ConnectionUtils {
	
	public static String getTextFromUrl(URL url) throws IOException {
		URLConnection connection = url.openConnection();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder builder = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				builder.append(line + "\n");
			}
			return builder.toString();
		} finally {
			if (reader != null)
				reader.close();
		}
	}
	
	private ConnectionUtils() {
	}
}
