package com.dutproject.coffee360admin.controller.shop;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dutproject.coffee360.model.bean.Tag;
import com.dutproject.coffee360admin.controller.BaseServlet;
import com.dutproject.coffee360admin.controller.Urls;
import com.dutproject.coffee360admin.model.bean.PlaceReportDetails;
import com.dutproject.coffee360admin.model.bo.PlaceReportBO;
import com.dutproject.coffee360admin.model.bo.TagBO;

@WebServlet(name = "EditCoffeeShopForm", urlPatterns = { "/EditCoffeeShopForm" })
public class EditCoffeeShopFormServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		PlaceReportBO placeReportBO = new PlaceReportBO();
		PlaceReportDetails coffeeShopReport = placeReportBO.getReportDetailsById(id);
		TagBO tagBO = new TagBO();
		List<Tag> tags = tagBO.getTagsById(id);
		
		request.setAttribute("coffeeShopReport", coffeeShopReport);
		request.setAttribute("tags", tags);
		request.getRequestDispatcher(Urls.SHOP_EDIT_SHOP).forward(request, response);
	}

}
