package com.solvd.library.persistence;

import com.solvd.library.domain.Comment;

import java.util.List;

public interface CommentRepository {
    Comment findById(int id);
    List<Comment> findAll();
    void save(Comment comment);
    void update(Comment comment);
    void delete(int id);
}
