package com.dutproject.coffee360admin.controller.place;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dutproject.coffee360.model.bean.Place;
import com.dutproject.coffee360.model.bean.PlaceReport;
import com.dutproject.coffee360.model.bean.Tag;
import com.dutproject.coffee360admin.controller.BaseServlet;
import com.dutproject.coffee360admin.controller.Urls;
import com.dutproject.coffee360admin.model.bean.PlaceDetails;
import com.dutproject.coffee360admin.model.bo.PlaceBO;
import com.dutproject.coffee360admin.model.bo.PlaceReportBO;
import com.dutproject.coffee360admin.model.bo.TagBO;

@WebServlet(name = "UpdatePlaceForm", urlPatterns = { "/UpdatePlaceForm" })
public class UpdatePlaceFormServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private PlaceReportBO placeReportBO = new PlaceReportBO();
	private PlaceBO placeBO = new PlaceBO();
	private TagBO tagBO = new TagBO();

	@Override
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		PlaceReport report = placeReportBO.getReport(id);
		Place place = placeBO.getPlace(report.getPlaceId());
		List<Tag> tags = tagBO.getTags(place.getTagIds());
		
		PlaceDetails details = new PlaceDetails(place, tags);

		request.setAttribute("details", details);
		request.getRequestDispatcher(Urls.UPDATE_PLACE).forward(request, response);
	}

}
