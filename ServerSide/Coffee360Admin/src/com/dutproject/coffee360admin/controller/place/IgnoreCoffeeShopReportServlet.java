package com.dutproject.coffee360admin.controller.place;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dutproject.coffee360admin.controller.BaseServlet;

@WebServlet(name = "IgnoreCoffeeShopReport", urlPatterns = { "/IgnoreCoffeeShopReport" })
public class IgnoreCoffeeShopReportServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
