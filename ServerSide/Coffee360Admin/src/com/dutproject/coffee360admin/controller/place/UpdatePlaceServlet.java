package com.dutproject.coffee360admin.controller.place;

import java.io.IOException;

import com.dutproject.coffee360.model.bean.Place;
import com.dutproject.coffee360admin.controller.login.FilterLoginServlet;
import com.dutproject.coffee360admin.model.bo.PlaceBO;
import com.dutproject.coffee360admin.model.bo.TagBO;
import com.dutproject.coffee360admin.util.Converter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UpdatePlace", urlPatterns = { "/UpdatePlace" })
public class UpdatePlaceServlet extends FilterLoginServlet {
	private static final long serialVersionUID = 1L;
	private PlaceBO placeBO = new PlaceBO();
	private TagBO tagBO = new TagBO();

	@Override
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int placeId = getPlaceId(request);
		Place place = placeBO.getPlace(placeId);
		updatePlaceInformation(place, request);
		boolean result = placeBO.update(place);
		request.getRequestDispatcher("PlaceReport").forward(request, response);
	}

	private void updatePlaceInformation(Place place, HttpServletRequest request) {
		String placeName = request.getParameter("placeName");
		String description = request.getParameter("description");
		int[] tagIds = tagBO .getTagIds(request.getParameter("tags"));
		
		place.setName(placeName);
		place.setDescription(description);
		place.setTagIds(tagIds);
	}

	private int getPlaceId(HttpServletRequest request) {
		return Converter.pareToInt(request.getParameter("placeId"), -1);
	}

}
