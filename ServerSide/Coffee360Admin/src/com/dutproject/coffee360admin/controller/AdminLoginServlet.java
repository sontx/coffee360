package com.dutproject.coffee360admin.controller;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;

import com.dutproject.coffee360admin.model.bean.AdminAccount;
import com.dutproject.coffee360admin.model.bo.AdminAccountBO;

/**
 * Servlet implementation class AdminLoginServlet
 */
@WebServlet("/Home")
public class AdminLoginServlet extends AdminServlet {
	private static final long serialVersionUID = 1L;

    public AdminLoginServlet() {
        super();
    }

	@Override
	protected void doWork() throws IOException {
		if (this.isLogined()) {
			this.gotoHomePage();
			return;
		}
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		AdminAccount adminAccount = new AdminAccount(username, password);
		
		AdminAccountBO adminAccountBO = new AdminAccountBO();
		if (adminAccountBO.isValidAccount(adminAccount)) {
			this.startSession(username);
			this.gotoHomePage();
		} else {
			this.gotoLoginPage();
		}
	}

}
