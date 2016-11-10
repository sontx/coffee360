package com.dutproject.coffee360.config;

import java.time.Duration;

public final class Constants {
	public static final String PROP_KEY_WEBAPP_PATH = "webapp_path";
	public static final String PROP_KEY_RESOURCE_DIR = "res_dir";
	public static final String PROP_KEY_RESOURCE_PHOTO_DIR_NAME = "photo_dir";
	public static final long EXPIRED_MILLIS = Duration.ofDays(7).toMillis();

	private Constants() {
	}
}
