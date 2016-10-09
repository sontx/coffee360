package com.dutproject.coffee360admin.model.bo;

import com.dutproject.coffee360admin.model.bean.AdminAccount;
import com.dutproject.coffee360admin.model.dao.AdminAccountDAO;

public class AdminAccountBO {
	private AdminAccountDAO adminAccountDAO = new AdminAccountDAO();
	
	public boolean isValidAccount(AdminAccount account) {
		return adminAccountDAO.isValidAccount(account);
	}
}
