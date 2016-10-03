package com.dutproject.coffee360;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.dutproject.coffee360.model.dao.jdbc.DatabaseManager;

@WebListener
public class StartupManager implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent arg0)  { 
         DatabaseManager.getInstance().release();
    }
    
    public void contextInitialized(ServletContextEvent arg0)  { 
    	DatabaseManager.getInstance();
    }
	
}
