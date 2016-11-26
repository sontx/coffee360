package com.dutproject.coffee360.model.dao.provider;

import java.sql.SQLException;
import java.util.List;

import com.dutproject.coffee360.model.bean.Comment;
import com.dutproject.coffee360.model.bean.CommentTable;

public interface ICommentProvider {
    List<Comment> getComments(int placeId, int fromIndex, int toIndex) throws SQLException;
    String getOwnerUsername(int commentId) throws SQLException;
    boolean isLiked(int userAccountId, int placeId) throws SQLException;
    List<CommentTable> getCommentTable(int placeId, int fromIndex, int toIndex) throws SQLException;
    boolean addComment(int placeId, int userAccountId, String message) throws SQLException;
}
