package com.dutproject.coffee360.model.dao.provider;

import java.sql.SQLException;

import com.dutproject.coffee360.model.bean.Account;

public interface IAuthenticationProvider {

	Account authenticateAdmin(String username, String password) throws SQLException;

}
