package com.solvd.library.persistence.impl;

import com.solvd.library.domain.Book;
import com.solvd.library.domain.Comment;
import com.solvd.library.domain.User;
import com.solvd.library.persistence.CommentRepository;
import com.solvd.library.persistence.CommentRepositoryRelationBook;
import com.solvd.library.persistence.CommentRepositoryRelationUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CommentJDBCImpl implements CommentRepository, CommentRepositoryRelationBook, CommentRepositoryRelationUser {
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
    public Object findById(Long id) {
        return null;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public void create(Object o) {

    }

    @Override
    public void update(Object o) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void linkEntities(Comment t, Book r) {

    }

    @Override
    public void unlinkEntities(Comment t, Book r) {

    }

    @Override
    public void linkEntities(Comment t, User r) {

    }

    @Override
    public void unlinkEntities(Comment t, User r) {

    }
}
