package com.dutproject.coffee360admin.controller.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dutproject.coffee360admin.controller.BaseServlet;
import com.dutproject.coffee360admin.controller.Urls;
import com.dutproject.coffee360admin.model.bean.AdminAccount;
import com.dutproject.coffee360admin.model.bo.AdminAccountBO;

@WebServlet("/Home")
public class LoginServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		AdminAccount adminAccount = new AdminAccount(username, password);
		
		AdminAccountBO adminAccountBO = new AdminAccountBO();
		if (adminAccountBO.isValidAccount(adminAccount)) {
			request.getSession().setAttribute("username", username);
			response.sendRedirect(Urls.REPORT_MANAGEMENT);
		} else {
			response.sendRedirect(Urls.LOGIN_FORM);
		}
	}

}
