package com.dutproject.coffee360admin.controller.photo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import com.dutproject.coffee360admin.controller.login.FilterLoginServlet;
import com.dutproject.coffee360admin.model.bo.PhotoReportBO;
import com.dutproject.coffee360admin.util.Converter;

@WebServlet(name = "ChangePhotoReportState", urlPatterns = { "/ChangePhotoReportState" })
public class ChangePhotoReportStateServlet extends FilterLoginServlet {
	private static final long serialVersionUID = 1L;
	private PhotoReportBO reportBO = new PhotoReportBO();

	@Override
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int reportId = getReportId(request);
		String state = getState(request);
		changeState(reportId, state);
		navigateToPhotoReports(request, response);
	}

	private void navigateToPhotoReports(HttpServletRequest request, ServletResponse response) 
			throws ServletException, IOException {
		request.getRequestDispatcher("PhotoReport").forward(request, response);
	}

	private void changeState(int reportId, String state) {
		reportBO.changeReportState(reportId, state);
	}

	private String getState(HttpServletRequest request) {
		return request.getParameter("state");
	}

	private int getReportId(HttpServletRequest request) {
		String sReportId = request.getParameter("id");
		int reportId = Converter.pareToInt(sReportId, -1);
		return reportId;
	}

}
