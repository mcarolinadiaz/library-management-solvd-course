package com.solvd.library.persistence;

import com.solvd.library.domain.*;
import com.solvd.library.domain.Comment;

import java.sql.Connection;
import java.util.List;

public interface CommentRepository<Comment> extends GenericDAO<Comment> {
    Comment findById(Long id);
    List<Comment> findAll();
    void create(Comment comment);
    void update(Comment comment);
}
