package com.solvd.library.persistence;

import com.solvd.library.domain.*;
import com.solvd.library.domain.Comment;

import java.sql.Connection;
import java.util.List;

public interface CommentRepository<Comment> extends GenericDAO<Comment> {
    Comment findById(Long id, Connection connection);
    List<Comment> findAll(Connection connection);
    void create(Comment comment, Connection connection);
    void update(Comment comment, Connection connection);
}
