package com.dutproject.coffee360admin.controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;

/**
 * Servlet implementation class AdminServlet
 */
public abstract class AdminServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
    }

    protected void startSession(String username) {
		this.setAttribute("username", username);
	}
    
    protected void stopSession() {
		this.request.getSession().invalidate();
	}
    
    protected boolean isLogined() {
		return this.request.getSession().getAttribute("username") != null;
	}
    
    protected void gotoHomePage() throws IOException {
		this.sendRedirect("jsp/adminReportManagement.jsp");
	}
    
    protected void gotoLoginPage() throws IOException {
    	this.sendRedirect("jsp/adminLoginForm.jsp");
	}
}
