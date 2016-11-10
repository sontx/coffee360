package com.dutproject.coffee360.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dutproject.coffee360.model.bean.Account;
import com.dutproject.coffee360.model.dao.provider.IAuthenticationProvider;

public class AuthenticationJdbcDAO extends JdbcBaseDAO implements IAuthenticationProvider {

	@Override
	public Account authenticateAdmin(String username, String password) throws SQLException {
		Connection connection = connectionProvider.getConnection();
		PreparedStatement prepareStatement = null;
		try {
			String sql = "SELECT account.accountId, " +
						 "accountpermission.permission " +
						 "FROM account INNER JOIN accountpermission " +
						 "ON account.username=? AND accountpermission.accountPermissionId=account.accountPermissionId " + 
						 "INNER JOIN adminaccount ON password=? AND adminaccount.accountId=account.accountId";
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, username);
			prepareStatement.setString(2, password);
			ResultSet resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				Account account = new Account();
				account.setId(resultSet.getInt("accountId"));
				String sPermission = resultSet.getString("permission");
				account.setPermission(Account.getPermissionFromString(sPermission));
				resultSet.close();
				return account;
			}
			resultSet.close();
			return null;
		} finally {
			if (prepareStatement != null)
				prepareStatement.close();
		}
	}

}
