package com.solvd.library.persistence.impl;

import com.solvd.library.domain.Comment;
import com.solvd.library.persistence.CommentRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentJDBCImpl implements CommentRepository {
    private static final Logger LOGGER = LogManager.getLogger(CommentJDBCImpl.class);
    private static final String JDBC_URL = "";
    private static final String JDBC_USER = "";
    private static final String JDBC_PASSWORD = "";
    private static final String SELECT_QUERY = "SELECT * FROM comments";
    private static final String DELETE_QUERY = "DELETE FROM comments WHERE id_comment = ?";
    private static final String INSERT_QUERY = "INSERT INTO comments (comment, id_book, id_user) VALUES (?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE comments SET comment = ? WHERE id_comment = ?";
    private Comment extractCommentFromResultSet(ResultSet resultSet) throws SQLException {
        Comment comment = new Comment();
        comment.setId(resultSet.getLong("id_comment"));
        comment.setComment(resultSet.getString("comment"));
        comment.setBookId(resultSet.getLong("id_book"));
        comment.setUserId(resultSet.getLong("id_user"));
        return comment;
    }
    @Override
    public Comment findById(Long id) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement =
                     connection.prepareStatement(SELECT_QUERY + " WHERE id_comment = ?")) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return this.extractCommentFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("SQL Exception while executing query: {}", e.getMessage());
        }
        return null;
    }

    @Override
    public List findAll() {
        List<Comment> comments = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SELECT_QUERY + ";")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    comments.add(extractCommentFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("SQL Exception while executing query: {}", e.getMessage());
        }
        return comments;
    }

    @Override
    public void create(Comment comment, Long bookId, Long userId) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {
            statement.setString(1, comment.getComment());
            statement.setLong(2, bookId);
            statement.setLong(3, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(Comment comment, Long bookId, Long userId) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setLong(1, bookId);
            statement.setLong(2, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
