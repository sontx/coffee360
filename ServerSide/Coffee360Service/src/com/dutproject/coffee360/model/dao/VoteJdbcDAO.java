package com.dutproject.coffee360.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.dutproject.coffee360.model.dao.provider.IVoteService;

public class VoteJdbcDAO extends JdbcBaseDAO implements IVoteService {

    @Override
    public int addVote(int userAccountId) throws SQLException {
        Connection connection = connectionProvider.getConnection();
        PreparedStatement prepareStatement = null;
        try {
            String sql = "INSERT INTO `vote`(`userAccountId`, `dateTime`) VALUES (?,NOW())";
            prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setInt(1, userAccountId);
            int i = prepareStatement.executeUpdate();
            boolean isSuccess = i > 0;
            if (isSuccess) {
                return getVoteId(userAccountId);
            } else 
            return -1;
        } finally {
            if (prepareStatement != null)
                prepareStatement.close();
        }
    }

    public int getVoteId(int userAccountId) throws SQLException {
        Connection connection = connectionProvider.getConnection();
        return getLastRowId(connection.createStatement(), "vote", "voteId");
    }

}
