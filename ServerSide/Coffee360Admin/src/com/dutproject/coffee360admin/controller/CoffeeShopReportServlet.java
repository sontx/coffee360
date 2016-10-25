package com.dutproject.coffee360admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dutproject.coffee360admin.model.bean.CoffeeShopReport;
import com.dutproject.coffee360admin.model.bo.CoffeeShopReportBO;

/**
 * Servlet implementation class AdminCoffeeShopReportServlet
 */
@WebServlet("/CoffeeShopReport")
public class CoffeeShopReportServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private static final int MAX_ENTRIES_PER_PAGE = 20;
    
	@Override
	protected void doWork(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!AdminServletHelper.isLogined(request.getSession())) {
			AdminServletHelper.gotoLoginPage(response);
			return;
		}
		
		/*get page number*/
		String str_pageNumber = request.getParameter("page");
		int pageNumber = 1;
		try {
			pageNumber = Integer.parseInt(str_pageNumber);
		} catch (Exception e) {
		}
		
		/*valid page number*/
		
		/*request coffee shop reports depend on page number*/
		CoffeeShopReportBO coffeeShopReportBO = new CoffeeShopReportBO();
		int totalOfCoffeeShopReport = coffeeShopReportBO.getTotalOfReports();
		int maxPageNumber = totalOfCoffeeShopReport / MAX_ENTRIES_PER_PAGE + 1;
		List<CoffeeShopReport> listCoffeeShopReports = coffeeShopReportBO.getListCoffeeShopReports(pageNumber, totalOfCoffeeShopReport);
		
		/*response reports*/
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("maxPageNumber", maxPageNumber);
		request.setAttribute("listCoffeeShopReports", listCoffeeShopReports);
		request.getRequestDispatcher("jsp/coffeeShopReportManagement.jsp").forward(request, response);
	}

}
