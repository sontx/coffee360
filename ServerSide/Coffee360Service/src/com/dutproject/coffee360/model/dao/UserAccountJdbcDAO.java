package com.dutproject.coffee360.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dutproject.coffee360.model.bean.UserAccountTable;
import com.dutproject.coffee360.model.dao.provider.IUserAccountProvider;

public class UserAccountJdbcDAO extends JdbcBaseDAO implements IUserAccountProvider {

    @Override
    public UserAccountTable getUserAccountTable(int userAccountId) throws SQLException {
        Connection connection = connectionProvider.getConnection();
        PreparedStatement prepareStatement = null;
        try {
            String sql = "SELECT * FROM `useraccount` WHERE userAccountId=?";
            prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setInt(1, userAccountId);
            ResultSet rs = prepareStatement.executeQuery();
            UserAccountTable userAccount = null;
            if (rs.next()) {
                userAccount = new UserAccountTable();
                userAccount.setUserAccountId(rs.getInt("userAccountId"));
                userAccount.setAccountId(rs.getInt("accountId"));
                userAccount.setAvatarId(rs.getInt("avatarId"));
                userAccount.setAvatarUrl(rs.getString("avatarUrl"));
                userAccount.setAccessToken(rs.getString("accessToken"));
                userAccount.setFullName(rs.getString("fullName"));
                userAccount.setAddress(rs.getString("address"));
                userAccount.setGender(rs.getString("gender"));
            }
            rs.close();
            return userAccount;
        } finally {
            if (prepareStatement != null)
                prepareStatement.close();
        }
    }

    public UserAccountTable getUserAccountByAccountId(int accountId) throws SQLException {
        Connection connection = connectionProvider.getConnection();
        PreparedStatement prepareStatement = null;
        try {
            String sql = "SELECT * FROM `useraccount` WHERE accountId=?";
            prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setInt(1, accountId);
            ResultSet rs = prepareStatement.executeQuery();
            UserAccountTable userAccount = null;
            if (rs.next()) {
                userAccount = new UserAccountTable();
                userAccount.setUserAccountId(rs.getInt("userAccountId"));
                userAccount.setAccountId(rs.getInt("accountId"));
                userAccount.setAvatarId(rs.getInt("avatarId"));
                userAccount.setAvatarUrl(rs.getString("avatarUrl"));
                userAccount.setAccessToken(rs.getString("accessToken"));
                userAccount.setFullName(rs.getString("fullName"));
                userAccount.setAddress(rs.getString("address"));
                userAccount.setGender(rs.getString("gender"));
            }
            rs.close();
            return userAccount;
        } finally {
            if (prepareStatement != null)
                prepareStatement.close();
        }
    }

}
