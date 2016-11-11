package com.dutproject.coffee360.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.dutproject.coffee360.config.Constants;

public final class FacebookOAuth {
	private static FacebookOAuth instance = null;

	public static FacebookOAuth getInstance() {
		if (instance == null)
			instance = new FacebookOAuth();
		return instance;
	}

	private FacebookOAuth() {
	}

	private URL getOAuthGraphUrl(String code, String redirectUri)
			throws UnsupportedEncodingException, MalformedURLException {
		String fbUrl = "https://graph.facebook.com/oauth/access_token?" + "client_id=" + Constants.OAUTH_FB_APP_ID
				+ "&redirect_uri=" + URLEncoder.encode(redirectUri, "UTF-8") + "&client_secret="
				+ Constants.OAUTH_FB_APP_SECRET + "&code=" + code;
		return new URL(fbUrl);
	}

	private URL getDataGraphUrl(String accessToken) throws MalformedURLException {
		String url = "https://graph.facebook.com/me?" + accessToken + "&fields=id,name,email,gender";
		return new URL(url);
	}

	public String requestAccessToken(String code, String redirectUri) throws IOException {
		URL facebookUrl = getOAuthGraphUrl(code, redirectUri);
		String accessToken = ConnectionUtils.getTextFromUrl(facebookUrl);
		if (accessToken != null && accessToken.endsWith("\n"))
			return accessToken.substring(0, accessToken.length() - 1);
		return accessToken;
	}

	public Map<String, String> getProfileData(String accessToken) throws IOException {
		URL dataGraphUrl = getDataGraphUrl(accessToken);
		String graphData = ConnectionUtils.getTextFromUrl(dataGraphUrl);
		Map<String, String> profileData = new HashMap<String, String>();
		JSONObject json = new JSONObject(graphData);
		profileData.put("id", json.getString("id"));
		profileData.put("mame", json.getString("name"));
		if (json.has("email"))
			profileData.put("email", json.getString("email"));
		if (json.has("gender"))
			profileData.put("gender", json.getString("gender"));
		return profileData;
	}
}
