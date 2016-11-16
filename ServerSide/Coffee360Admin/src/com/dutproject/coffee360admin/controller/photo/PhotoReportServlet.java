package com.dutproject.coffee360admin.controller.photo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dutproject.coffee360admin.controller.Urls;
import com.dutproject.coffee360admin.controller.login.FilterLoginServlet;
import com.dutproject.coffee360admin.model.bean.PhotoReportDetails;
import com.dutproject.coffee360admin.model.bo.PhotoReportBO;

@WebServlet("/PhotoReport")
public class PhotoReportServlet extends FilterLoginServlet {
	private static final long serialVersionUID = 1L;
	public static final int MAX_ENTRIES_PER_PAGE = 20;
	private PhotoReportBO photoReportBO = new PhotoReportBO();

	@Override
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int maxPageNumber = getMaxPageNumber();
		int pageNumber = getPageNumber(request, maxPageNumber);
		List<PhotoReportDetails> listReportDetails = photoReportBO.getListReports(pageNumber);
		
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("maxPageNumber", maxPageNumber);
		request.setAttribute("listReportDetails", listReportDetails);
		request.getRequestDispatcher(Urls.PHOTO_REPORTS).forward(request, response);
	}

	private int getMaxPageNumber() {
		int totalOfPhotoReport = photoReportBO.getNumberOfReport();
		int maxPageNumber = (totalOfPhotoReport + MAX_ENTRIES_PER_PAGE - 1) / MAX_ENTRIES_PER_PAGE;
		return maxPageNumber;
	}

	private int getPageNumber(HttpServletRequest request, int maxPageNumber) {
		String str_pageNumber = request.getParameter("page");
		int pageNumber;
		try {
			pageNumber = Integer.parseInt(str_pageNumber);
		} catch (Exception e) {
			pageNumber = 1;
		}
		if (pageNumber <= 0) {
			pageNumber = 1;
		} else if (pageNumber > maxPageNumber) {
			pageNumber = maxPageNumber;
		}
		return pageNumber;
	}

}
