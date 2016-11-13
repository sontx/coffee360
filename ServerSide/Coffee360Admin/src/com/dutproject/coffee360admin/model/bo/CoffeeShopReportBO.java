package com.dutproject.coffee360admin.model.bo;

import java.util.List;

import com.dutproject.coffee360admin.model.bean.ShopReport;
import com.dutproject.coffee360admin.model.dao.CoffeeShopReportDAO;

public class CoffeeShopReportBO {
	private CoffeeShopReportDAO coffeeShopReportDAO = new CoffeeShopReportDAO();

	public int getNumberOfCoffeeShopReport() {
		return coffeeShopReportDAO.getNumberOfCoffeeShopReport();
	}

	public List<ShopReport> getListCoffeeShopReports(int pageNumber) {
		return coffeeShopReportDAO.getListCoffeeShopReports(pageNumber);
	}

	public ShopReport getCoffeeShopReportById(int id) {
		return coffeeShopReportDAO.getReportByReportId(id);
	}

}
