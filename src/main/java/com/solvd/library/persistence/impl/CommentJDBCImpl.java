package com.solvd.library.persistence.impl;

import com.solvd.library.domain.Comment;
import com.solvd.library.persistence.CommentRepository;
import com.solvd.library.persistence.MyConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * JDBC implementation of the CommentRepository interface.
 */
public class CommentJDBCImpl implements CommentRepository {

    private static final Logger LOGGER = LogManager.getLogger(CommentJDBCImpl.class);
    private static final String SELECT_QUERY = "SELECT * FROM comments";
    private static final String DELETE_QUERY = "DELETE FROM comments WHERE id_comment = ?";
    private static final String INSERT_QUERY = "INSERT INTO comments (comment, id_book, id_user) VALUES (?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE comments SET comment = ? WHERE id_comment = ?";
    private static final Connection connection;

    static {
        try {
            // Initialize the connection using MyConnectionPool
            connection = MyConnectionPool.getConnection();
        } catch (InterruptedException e) {
            // Handle connection initialization exception
            throw new RuntimeException(e);
        }
    }

    /**
     * Extracts a Comment object from the ResultSet.
     *
     * @param resultSet The ResultSet containing Comment data.
     * @return A Comment object.
     * @throws SQLException If a SQL exception occurs.
     */
    private Comment extractCommentFromResultSet(ResultSet resultSet) throws SQLException {
        Comment comment = new Comment();
        comment.setId(resultSet.getLong("id_comment"));
        comment.setComment(resultSet.getString("comment"));
        comment.setBookId(resultSet.getLong("id_book"));
        comment.setUserId(resultSet.getLong("id_user"));
        return comment;
    }

    @Override
    public Optional<Comment> findById(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_QUERY + " WHERE id_comment = ?")) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(extractCommentFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            // Handle SQL exception
            LOGGER.error("SQL Exception while executing query: {}", e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Comment> findAll() {
        List<Comment> comments = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_QUERY + ";")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    comments.add(extractCommentFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            // Handle SQL exception
            LOGGER.error("SQL Exception while executing query: {}", e.getMessage());
        }
        return comments;
    }

    /**
     * Configures the SQL declaration with entity data and executes it to create comments.
     *
     * @param comment The Comment to be created.
     * @param bookId  The ID of the associated book.
     * @param userId  The ID of the associated user.
     */
    @Override
    public void create(Comment comment, Long bookId, Long userId) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {
            statement.setString(1, comment.getComment());
            statement.setLong(2, bookId);
            statement.setLong(3, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            // Handle SQL exception
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(Comment comment, Long bookId, Long userId) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, comment.getComment());
            statement.setLong(2, comment.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            // Handle SQL exception
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            // Handle SQL exception
            LOGGER.error(e.getMessage());
        }
    }
}
