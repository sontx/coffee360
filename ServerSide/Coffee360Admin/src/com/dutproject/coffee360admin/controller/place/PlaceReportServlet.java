package com.dutproject.coffee360admin.controller.place;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dutproject.coffee360admin.controller.Urls;
import com.dutproject.coffee360admin.controller.login.FilterLoginServlet;
import com.dutproject.coffee360admin.model.bean.PlaceReportDetails;
import com.dutproject.coffee360admin.model.bo.PlaceReportBO;

@WebServlet("/PlaceReport")
public class PlaceReportServlet extends FilterLoginServlet {
	private static final long serialVersionUID = 1L;
	public static final int MAX_ENTRIES_PER_PAGE = 20;
	private PlaceReportBO placeReportBO = new PlaceReportBO();
    
	@Override
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int maxPageNumber = getMaxPageNumber();
		int pageNumber = getPageNumber(request, maxPageNumber);
		List<PlaceReportDetails> listPlaceReportDetails = placeReportBO.getListReportDetails(pageNumber);
		
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("maxPageNumber", maxPageNumber);
		request.setAttribute("listPlaceReportDetails", listPlaceReportDetails);
		request.getRequestDispatcher(Urls.PLACE_REPORTS).forward(request, response);
	}

	private int getMaxPageNumber() {
		int totalOfCoffeeShopReport = placeReportBO.getNumberOfReport();
		int maxPageNumber = (totalOfCoffeeShopReport + MAX_ENTRIES_PER_PAGE - 1) / MAX_ENTRIES_PER_PAGE;
		return maxPageNumber;
	}

	private int getPageNumber(HttpServletRequest request, int maxPageNumber) {
		String str_pageNumber = request.getParameter("page");
		int pageNumber = 1;
		try {
			pageNumber = Integer.parseInt(str_pageNumber);
		} catch (Exception e) {
		}
		if (pageNumber <= 0) {
			pageNumber = 1;
		} else if (pageNumber > maxPageNumber) {
			pageNumber = maxPageNumber;
		}
		return pageNumber;
	}

}
