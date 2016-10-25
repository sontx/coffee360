package com.dutproject.coffee360admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditCoffeeShopFormServlet
 */
@WebServlet(name = "EditCoffeeShopForm", urlPatterns = { "/EditCoffeeShopForm" })
public class EditCoffeeShopFormServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doWork(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("jsp/editCoffeeShopForm.jsp");
	}

}
