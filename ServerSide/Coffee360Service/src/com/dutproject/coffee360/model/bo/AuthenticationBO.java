package com.dutproject.coffee360.model.bo;

import java.sql.SQLException;

import com.dutproject.coffee360.model.bean.Account;
import com.dutproject.coffee360.model.bean.UserAccount;
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

	public UserAccount getAccountByUserName(String accessToken) throws SQLException {
		return authenticationDAO.getAccountByUserName(accessToken);
	}

	public UserAccount createNewUserAccount(UserAccount newUserAccount) throws SQLException {
		return authenticationDAO.createNewUserAccount(newUserAccount);
	}

	public UserAccount authenticateUser(String token) throws Exception {
		TokensManager.getInstance().validateToken(token);
		int accountId = TokensManager.getInstance().getAccountIdByToken(token);
		return authenticationDAO.getUserAccountById(accountId);
	}

	public int getPermissionByAccountId(int accountId) throws SQLException {
		return authenticationDAO.getPermissionByAccountId(accountId);
	}

	public void updateAvatarUrl(int accountId, String avatarUrl) throws SQLException {
		authenticationDAO.updateAvatarUrl(accountId, avatarUrl);
	}

}
