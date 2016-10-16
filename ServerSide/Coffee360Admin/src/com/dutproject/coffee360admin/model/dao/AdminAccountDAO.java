package com.dutproject.coffee360admin.model.dao;

import com.dutproject.coffee360admin.model.bean.AdminAccount;

public class AdminAccountDAO {

	public boolean isValidAccount(AdminAccount account) {
		if ("trongvn13".equals(account.getUsername()) && "123".equals(account.getPassword())) {
			return true;
		}
		return false;
	}

}
