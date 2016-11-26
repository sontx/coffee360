package com.dutproject.coffee360.model.dao.provider;

import java.sql.SQLException;

import com.dutproject.coffee360.model.bean.Account;
import com.dutproject.coffee360.model.bean.UserAccount;

public interface IAuthenticationProvider {

	Account authenticateAdmin(String username, String password) throws SQLException;

	UserAccount getAccountByUserName(String username) throws SQLException;

	UserAccount createNewUserAccount(UserAccount newUserAccount) throws SQLException;

	UserAccount getUserAccountById(int accountId) throws SQLException;

	int getPermissionByAccountId(int accountId) throws SQLException;

	void updateAvatarUrl(int accountId, String avatarUrl) throws SQLException;

}
