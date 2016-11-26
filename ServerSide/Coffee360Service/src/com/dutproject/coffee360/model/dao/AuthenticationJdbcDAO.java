package com.dutproject.coffee360.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.dutproject.coffee360.model.bean.Account;
import com.dutproject.coffee360.model.bean.UserAccount;
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

	@Override
	public UserAccount getAccountByUserName(String username) throws SQLException {
		Connection connection = connectionProvider.getConnection();
		PreparedStatement prepareStatement = null;
		try {
			String sql = "SELECT account.accountId, account.username, " +
						 "accountpermission.permission, " +
						 "useraccount.address, useraccount.avatarId, useraccount.fullName, useraccount.gender, useraccount.avatarUrl " +
						 "FROM account INNER JOIN accountpermission " +
						 "ON accountpermission.accountPermissionId=account.accountPermissionId " + 
						 "INNER JOIN useraccount ON useraccount.accountId=account.accountId " +
						 "WHERE username=?";
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, username);
			ResultSet resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				UserAccount account = new UserAccount();
				
				account.setId(resultSet.getInt("accountId"));
				String sPermission = resultSet.getString("permission");
				account.setPermission(Account.getPermissionFromString(sPermission));
				account.setUserName(resultSet.getString("username"));
				account.setAddress(resultSet.getString("address"));
				account.setAvatarId(resultSet.getInt("avatarId"));
				account.setFullName(resultSet.getString("fullName"));
				account.setGender(resultSet.getString("gender").equals("f") ? "fimale" : "male");
				account.setAvatarUrl(resultSet.getString("avatarUrl"));
				
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

	@Override
	public UserAccount createNewUserAccount(UserAccount newUserAccount) throws SQLException {
		Connection connection = connectionProvider.getConnection();
		PreparedStatement prepareStatement = null;
		Statement statement = null;
		try {
			String sql = "INSERT INTO account(accountPermissionId, username) VALUES(?,?)";
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, newUserAccount.getPermission());
			prepareStatement.setString(2, newUserAccount.getUserName());
			prepareStatement.executeUpdate();
			statement = connection.createStatement();
			int accountId = getLastRowId(statement, "account", "accountId");
			prepareStatement.close();
			
			sql = "INSERT INTO useraccount(accessToken, accountId, address, fullName, gender, avatarUrl) VALUES(?,?,?,?,?,?)";
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, newUserAccount.getAccessToken());
			prepareStatement.setInt(2, accountId);
			prepareStatement.setString(3, newUserAccount.getAddress());
			prepareStatement.setString(4, newUserAccount.getFullName());
			prepareStatement.setString(5, newUserAccount.getGender().substring(0, 1));
			prepareStatement.setString(6, newUserAccount.getAvatarUrl());
			prepareStatement.executeUpdate();
			
			newUserAccount.setId(accountId);
			return newUserAccount;
		} finally {
			if (prepareStatement != null)
				prepareStatement.close();
			if (statement != null)
				statement.close();
		}
	}

	@Override
	public UserAccount getUserAccountById(int accountId) throws SQLException {
		Connection connection = connectionProvider.getConnection();
		PreparedStatement prepareStatement = null;
		try {
			String sql = "SELECT account.accountId, account.username, " +
						 "accountpermission.permission, " +
						 "useraccount.address, useraccount.avatarId, useraccount.fullName, useraccount.gender, useraccount.avatarUrl " +
						 "FROM account INNER JOIN accountpermission " +
						 "ON accountpermission.accountPermissionId=account.accountPermissionId " + 
						 "INNER JOIN useraccount ON useraccount.accountId=account.accountId " +
						 "WHERE account.accountId=?";
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, accountId);
			ResultSet resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				UserAccount account = new UserAccount();
				
				account.setId(resultSet.getInt("accountId"));
				String sPermission = resultSet.getString("permission");
				account.setPermission(Account.getPermissionFromString(sPermission));
				account.setUserName(resultSet.getString("username"));
				account.setAddress(resultSet.getString("address"));
				account.setAvatarId(resultSet.getInt("avatarId"));
				account.setFullName(resultSet.getString("fullName"));
				account.setGender(resultSet.getString("gender").equals("f") ? "fimale" : "male");
				account.setAvatarUrl(resultSet.getString("avatarUrl"));
				
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

	@Override
	public int getPermissionByAccountId(int accountId) throws SQLException {
		Connection connection = connectionProvider.getConnection();
		PreparedStatement prepareStatement = null;
		try {
			String sql = "SELECT accountpermission.permission " +
						 "FROM account INNER JOIN accountpermission " +
						 "ON accountpermission.accountPermissionId=account.accountPermissionId " + 
						 "WHERE account.accountId=?";
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, accountId);
			ResultSet resultSet = prepareStatement.executeQuery();
			int permission = Account.PERMISSION_GUEST;
			if (resultSet.next()) {
				String spermission = resultSet.getString("permission");
				permission = "Admin".equals(spermission) ? Account.PERMISSION_ADMIN : ("User".equals(spermission) ? Account.PERMISSION_USER : Account.PERMISSION_GUEST);
			}
			resultSet.close();
			return permission;
		} finally {
			if (prepareStatement != null)
				prepareStatement.close();
		}
	}

}
