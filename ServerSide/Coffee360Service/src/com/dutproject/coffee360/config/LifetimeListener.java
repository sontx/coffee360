package com.dutproject.coffee360.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class LifetimeListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent event) {
		
	}

	public void contextInitialized(ServletContextEvent event) {
		String webappPath = event.getServletContext().getRealPath("");
		System.getProperties().put(Constants.PROP_KEY_WEBAPP_PATH, webappPath);
		System.getProperties().put(Constants.PROP_KEY_RESOURCE_DIR, "D:\\test");
		System.getProperties().put(Constants.PROP_KEY_RESOURCE_PHOTO_DIR_NAME, "photos");
	}

}
