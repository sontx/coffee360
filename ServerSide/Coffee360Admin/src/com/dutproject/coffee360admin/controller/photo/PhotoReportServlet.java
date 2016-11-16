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
		List<PhotoReportDetails> details = photoReportBO.getListReports(pageNumber);
		
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("maxPageNumber", maxPageNumber);
		request.setAttribute("details", details);
		request.getRequestDispatcher(Urls.PHOTO_REPORTS).forward(request, response);
	}

	private int getMaxPageNumber() {
		int total = photoReportBO.getCountReport();
		int max = (total + MAX_ENTRIES_PER_PAGE - 1) / MAX_ENTRIES_PER_PAGE;
		return max;
	}

	private int getPageNumber(HttpServletRequest request, int maxPageNumber) {
		String str_page = request.getParameter("page");
		int page;
		try {
			page = Integer.parseInt(str_page);
		} catch (Exception e) {
			page = 1;
		}
		if (page <= 0) {
			page = 1;
		} else if (page > maxPageNumber) {
			page = maxPageNumber;
		}
		return page;
	}

}
