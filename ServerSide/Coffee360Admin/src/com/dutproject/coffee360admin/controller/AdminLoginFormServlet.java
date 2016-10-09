package com.dutproject.coffee360admin.controller;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class AdminLoginFormServlet
 */
@WebServlet("/Login")
public class AdminLoginFormServlet extends AdminServlet {
	private static final long serialVersionUID = 1L;

    public AdminLoginFormServlet() {
        super();
    }

	@Override
	protected void doWork() throws IOException {
		this.sendRedirect("jsp/adminLoginForm.jsp");
	}

}
