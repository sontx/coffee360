package com.dutproject.coffee360.model.bo;

import java.sql.SQLException;
import java.util.List;

import com.dutproject.coffee360.model.bean.Comment;
import com.dutproject.coffee360.model.dao.CommentJdbcDAO;
import com.dutproject.coffee360.model.dao.provider.ICommentProvider;

public class CommentBO {
    private ICommentProvider commentProvider = new CommentJdbcDAO();

    public List<Comment> getComments(int placeId, int fromIndex, int toIndex) throws SQLException {
        return commentProvider.getComments(placeId, fromIndex, toIndex);
    }

    public boolean addComment(int placeId, int userAccountId, String message) throws SQLException {
        return commentProvider.addComment(placeId, userAccountId, message);
    }

}
