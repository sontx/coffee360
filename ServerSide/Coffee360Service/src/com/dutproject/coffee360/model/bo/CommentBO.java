package com.dutproject.coffee360.model.bo;

import java.sql.SQLException;
import java.util.List;

import com.dutproject.coffee360.model.bean.Comment;
import com.dutproject.coffee360.model.bean.NewComment;
import com.dutproject.coffee360.model.bean.NewCommentVote;
import com.dutproject.coffee360.model.dao.CommentJdbcDAO;
import com.dutproject.coffee360.model.dao.UserAccountJdbcDAO;
import com.dutproject.coffee360.model.dao.provider.ICommentProvider;

public class CommentBO {
    private ICommentProvider commentProvider = new CommentJdbcDAO();

    public List<Comment> getComments(int placeId, int fromIndex, int toIndex) throws SQLException {
        return commentProvider.getComments(placeId, fromIndex, toIndex);
    }

    public String addComment(int placeId, int userAccountId, String message) throws SQLException {
        return commentProvider.addComment(placeId, userAccountId, message);
    }

    public String addComment(int accountId, NewComment comment) {
        try {
            int userAccountId = new UserAccountJdbcDAO().getUserAccountByAccountId(accountId).getUserAccountId();
            return addComment(comment.getPlaceId(), userAccountId, comment.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String voteComment(NewCommentVote vote) throws SQLException {
        return commentProvider.voteComment(vote.getUserAccountId(), vote.getCommentId());
    }

}
