package com.dutproject.coffee360admin.controller.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dutproject.coffee360admin.controller.BaseServlet;
import com.dutproject.coffee360admin.controller.Urls;

@WebServlet("/Logout")
public class LogoutServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect(Urls.LOGIN_FORM);
	}

}
