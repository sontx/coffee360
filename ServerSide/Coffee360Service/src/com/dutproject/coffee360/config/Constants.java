package com.dutproject.coffee360.config;

import java.time.Duration;

public final class Constants {
	public static final String PROP_KEY_WEBAPP_PATH = "webapp_path";
	public static final String PROP_KEY_RESOURCE_DIR = "res_dir";
	public static final String PROP_KEY_RESOURCE_PHOTO_DIR_NAME = "photo_dir";
	public static final long EXPIRED_MILLIS = Duration.ofDays(7).toMillis();
	public static final String OAUTH_FB_APP_ID = "1348420915176737";
	public static final String OAUTH_FB_APP_SECRET = "cd71aa41b72f993ff2b1a462f33cd08c";
	public static final String OAUTH_REDIRECT_URI = "http://sontx.no-ip.org:8080/Coffee360Service/rest/v1/auth/oauth";
		
	private Constants() {
	}
}
