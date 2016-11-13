package com.dutproject.coffee360admin.controller.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dutproject.coffee360admin.controller.BaseServlet;
import com.dutproject.coffee360admin.controller.Urls;

public abstract class FilterLoginServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		filterLogin(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		filterLogin(request, response);
	}

	private void filterLogin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (checkLogin(request)) {
			processRequest(request, response);
		} else {
			response.sendRedirect(Urls.LOGIN_FORM);
		}
	}

	private boolean checkLogin(HttpServletRequest request) {
		return request.getSession().getAttribute("username") != null;
	}

}
