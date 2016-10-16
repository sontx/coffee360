package com.dutproject.coffee360admin.controller;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class AdminLogoutServlet
 */
@WebServlet("/Logout")
public class AdminLogoutServlet extends AdminServlet {
	private static final long serialVersionUID = 1L;

    public AdminLogoutServlet() {
        super();
    }

	@Override
	protected void doWork() throws IOException {
		this.stopSession();
		this.sendRedirect("jsp/adminLoginForm.jsp");
	}

}
