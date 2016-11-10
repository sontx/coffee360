package com.dutproject.coffee360.model.bo;

import java.sql.SQLException;

import com.dutproject.coffee360.model.bean.Account;
import com.dutproject.coffee360.model.dao.AuthenticationJdbcDAO;
import com.dutproject.coffee360.model.dao.provider.IAuthenticationProvider;
import com.dutproject.coffee360.utils.TokensManager;

public class AuthenticationBO {
	private IAuthenticationProvider authenticationDAO = new AuthenticationJdbcDAO();
		
	public Account authenticateAdmin(String username, String password) throws SQLException {
		return authenticationDAO.authenticateAdmin(username, password);
	}

	public String issueToken(Account account) {
		return TokensManager.getInstance().issueToken(account);
	}

}
