package com.dutproject.coffee360.model.dao.provider;

import java.sql.SQLException;

public interface IVoteService {
    int addVote(int userAccountId) throws SQLException;
}
