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
		String url = "https://graph.facebook.com/me?access_token=" + accessToken + "&fields=id,name,email,gender,location";
		return new URL(url);
	}
	
	private String getAvatarUrl(String userId) throws IOException {
		String surl = "http://graph.facebook.com/" + userId + "/?fields=picture&type=large&redirect=false";
		URL url = new URL(surl);
		String json = ConnectionUtils.getTextFromUrl(url);
		JSONObject jsonObject = new JSONObject(json);
		return jsonObject.getJSONObject("data").getString("url");
	}

	public String requestAccessToken(String code, String redirectUri) throws IOException {
		URL facebookUrl = getOAuthGraphUrl(code, redirectUri);
		String accessToken = ConnectionUtils.getTextFromUrl(facebookUrl);
		if (accessToken == null)
			return null;
		if (accessToken.endsWith("\n"))
			accessToken = accessToken.substring(0, accessToken.length() - 1);
		accessToken = accessToken.substring("access_token=".length(), accessToken.indexOf("&expires"));
		return accessToken;
	}

	public Map<String, String> getProfileData(String accessToken) throws IOException {
		URL dataGraphUrl = getDataGraphUrl(accessToken);
		String graphData = ConnectionUtils.getTextFromUrl(dataGraphUrl);
		Map<String, String> profileData = new HashMap<String, String>();
		JSONObject json = new JSONObject(graphData);
		String sid = json.getString("id");
		profileData.put("id", sid);
		profileData.put("name", json.getString("name"));
		if (json.has("email"))
			profileData.put("email", json.getString("email"));
		if (json.has("gender"))
			profileData.put("gender", json.getString("gender"));
		if (json.has("location")) {
			JSONObject locationObject = json.getJSONObject("location");
			profileData.put("location", locationObject.getString("name"));
		}
		profileData.put("avatarUrl", getAvatarUrl(sid));
		return profileData;
	}
}
