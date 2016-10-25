package com.dutproject.coffee360admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dutproject.coffee360admin.model.bean.AdminAccount;
import com.dutproject.coffee360admin.model.bo.AdminAccountBO;

/**
 * Servlet implementation class AdminLoginServlet
 */
@WebServlet("/Home")
public class LoginServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doWork(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (AdminServletHelper.isLogined(request.getSession())) {
			AdminServletHelper.gotoHomePage(response);
			return;
		}
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		AdminAccount adminAccount = new AdminAccount(username, password);
		
		AdminAccountBO adminAccountBO = new AdminAccountBO();
		if (adminAccountBO.isValidAccount(adminAccount)) {
			AdminServletHelper.startSession(request.getSession(), username);
			AdminServletHelper.gotoHomePage(response);
		} else {
			AdminServletHelper.gotoLoginPage(response);
		}
	}

}
