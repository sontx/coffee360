package com.dutproject.coffee360.model.dao.provider;

import java.sql.SQLException;

import com.dutproject.coffee360.model.bean.UserAccountTable;

public interface IUserAccountProvider {
    UserAccountTable getUserAccountTable(int userAccountId) throws SQLException;
}
