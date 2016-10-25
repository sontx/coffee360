package com.dutproject.coffee360admin.model.bo;

import java.util.List;

import com.dutproject.coffee360admin.model.bean.CoffeeShopReport;
import com.dutproject.coffee360admin.model.dao.CoffeeShopReportDAO;

public class CoffeeShopReportBO {
	private CoffeeShopReportDAO coffeeShopReportDAO = new CoffeeShopReportDAO();

	public int getTotalOfReports() {
		return coffeeShopReportDAO.getTotalOfReports();
	}

	public List<CoffeeShopReport> getListCoffeeShopReports(int pageNumber, int totalOfCoffeeShopReport) {
		return coffeeShopReportDAO.getListCoffeeShopReports(pageNumber, totalOfCoffeeShopReport);
	}

}
