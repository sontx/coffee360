package com.dutproject.coffee360admin.controller.place;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dutproject.coffee360admin.controller.login.FilterLoginServlet;
import com.dutproject.coffee360admin.model.bo.PlaceReportBO;
import com.dutproject.coffee360admin.util.Converter;

@WebServlet(name = "ChangePlaceReportState", urlPatterns = { "/ChangePlaceReportState" })
public class ChangePlaceReportStateServlet extends FilterLoginServlet {
	private static final long serialVersionUID = 1L;
	private PlaceReportBO reportBO = new PlaceReportBO();

	@Override
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int reportId = getReportId(request);
		String state = getState(request);
		
		boolean result = reportBO.changeState(reportId, state);
		
		request.setAttribute("isChangedState", result);
		request.getRequestDispatcher("PlaceReport").forward(request, response);
	}

	private String getState(HttpServletRequest request) {
		return request.getParameter("state");
	}

	private int getReportId(HttpServletRequest request) {
		String sReportId = request.getParameter("reportId");
		return Converter.pareToInt(sReportId, -1);
	}

}
