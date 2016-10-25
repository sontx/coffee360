package com.dutproject.coffee360admin.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AdminServlet
 */
public abstract class AdminServletHelper {
	
    public static void startSession(HttpSession session, String username) {
    	session.setAttribute("username", username);
	}
    
    public static void stopSession(HttpSession session) {
    	session.invalidate();
	}
    
    public static boolean isLogined(HttpSession session) {
    	return session.getAttribute("username") != null;
	}
    
    public static void gotoHomePage(HttpServletResponse response) throws IOException {
    	response.sendRedirect("jsp/reportManagement.jsp");
	}
    
    public static void gotoLoginPage(HttpServletResponse response) throws IOException {
    	response.sendRedirect("jsp/loginForm.jsp");
	}
}
