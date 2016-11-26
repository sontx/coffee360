    package com.dutproject.coffee360.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dutproject.coffee360.model.bean.Comment;
import com.dutproject.coffee360.model.bean.CommentTable;
import com.dutproject.coffee360.model.dao.provider.ICommentProvider;

public class CommentJdbcDAO extends JdbcBaseDAO implements ICommentProvider {
    private UserAccountJdbcDAO userAccountDAO = new UserAccountJdbcDAO();

    @Override
    public List<Comment> getComments(int placeId, int fromIndex, int toIndex) throws SQLException {
        List<Comment> listComments = new ArrayList<>();
        List<CommentTable> commentTables = getCommentTable(placeId, fromIndex, toIndex);
        for (CommentTable table : commentTables) {
            Comment comment = new Comment();
            comment.setId(table.getCommentId());
            comment.setContent(table.getMessage());
            comment.setLiked(isLiked(table.getUserAccountId(), placeId));
            comment.setOwnerUsername(getOwnerUsername(table.getCommentId()));
            comment.setAvatarUrl(userAccountDAO.getUserAccountTable(table.getUserAccountId()).getAvatarUrl());
            listComments.add(comment);
        }
        return listComments;
    }
    

    @Override
    public String getOwnerUsername(int commentId) throws SQLException {
        Connection connection = connectionProvider.getConnection();
        PreparedStatement prepareStatement = null;
        try {
            String sql = "SELECT useraccount.fullName FROM `useraccount` INNER JOIN `comment` " + 
                    "ON useraccount.userAccountId=comment.userAccountId " + 
                    "WHERE comment.commentId=?";
            prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setInt(1, commentId);
            ResultSet rs = prepareStatement.executeQuery();
            String fullName = null;
            if (rs.next())
                fullName = rs.getString(1);
            rs.close();
            return fullName;
        } finally {
            if (prepareStatement != null)
                prepareStatement.close();
        }
    }
    

    @Override
    public boolean isLiked(int userAccountId, int placeId) throws SQLException {
        Connection connection = connectionProvider.getConnection();
        PreparedStatement prepareStatement = null;
        try {
            String sql = "SELECT vote.userAccountId FROM `vote` INNER JOIN `commentvote` INNER JOIN `comment` " +
                    "ON vote.voteId=commentvote.voteId AND commentvote.commentId=comment.commentId " +
                    "WHERE vote.userAccountId=? AND comment.placeId=?";
            prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setInt(1, userAccountId);
            prepareStatement.setInt(2, placeId);
            ResultSet rs = prepareStatement.executeQuery();
            boolean result = false;
            if (rs.next())
                result = true;
            rs.close();
            return result;
        } finally {
            if (prepareStatement != null)
                prepareStatement.close();
        }
    }
    

    @Override
    public List<CommentTable> getCommentTable(int placeId, int fromIndex, int toIndex) throws SQLException {
        Connection connection = connectionProvider.getConnection();
        PreparedStatement prepareStatement = null;
        try {
            String sql = "SELECT * FROM `comment` WHERE placeId=? LIMIT ? OFFSET ?";
            prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setInt(1, placeId);
            prepareStatement.setInt(2, fromIndex + toIndex + 1);
            prepareStatement.setInt(3, fromIndex);
            ResultSet rs = prepareStatement.executeQuery();
            List<CommentTable> list = new ArrayList<>();
            while (rs.next()) {
                CommentTable result = new CommentTable();
                result.setCommentId(rs.getInt("commentId"));
                result.setPlaceId(rs.getInt("placeId"));
                result.setUserAccountId(rs.getInt("userAccountId"));
                result.setMessage(rs.getString("message"));
                result.setDatetime(rs.getDate("datetime"));
                list.add(result);
            }
            rs.close();
            return list;
        } finally {
            if (prepareStatement != null)
                prepareStatement.close();
        }
    }


    
    @Override
    public boolean addComment(int placeId, int userAccountId, String message) throws SQLException {
        Connection connection = connectionProvider.getConnection();
        PreparedStatement prepareStatement = null;
        try {
            String sql = "INSERT INTO `comment`(`placeId`, `userAccountId`, `message`, `dateTime`) VALUES (?,?,?,NOW())";
            prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setInt(1, placeId);
            prepareStatement.setInt(2, userAccountId);
            prepareStatement.setString(3, message);
            int i = prepareStatement.executeUpdate();
            boolean result = i > 0;
            return result;
        } finally {
            if (prepareStatement != null)
                prepareStatement.close();
        }
    }
    
}
