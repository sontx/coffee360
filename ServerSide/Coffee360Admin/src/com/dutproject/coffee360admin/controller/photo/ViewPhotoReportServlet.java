package com.dutproject.coffee360admin.controller.photo;

import java.io.IOException;

import com.dutproject.coffee360admin.controller.Urls;
import com.dutproject.coffee360admin.controller.login.FilterLoginServlet;
import com.dutproject.coffee360admin.model.bean.PhotoReportDetails;
import com.dutproject.coffee360admin.model.bo.PhotoReportBO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ViewPhotoReport", urlPatterns = { "/ViewPhotoReport" })
public class ViewPhotoReportServlet extends FilterLoginServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int reportId = getReportId(request);
		PhotoReportBO reportBO = new PhotoReportBO();
		PhotoReportDetails detail = reportBO.getReportDetailsById(reportId);
		request.setAttribute("detail", detail);
		request.getRequestDispatcher(Urls.PHOTO_REPORT).forward(request, response);
	}

	private int getReportId(HttpServletRequest request) {
		return Integer.parseInt(request.getParameter("id"));
	}

}
