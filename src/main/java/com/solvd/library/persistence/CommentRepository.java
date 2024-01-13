package com.solvd.library.persistence;



import com.solvd.library.domain.Comment;

import java.util.List;

public interface CommentRepository extends GenericDAO<Comment> {
    Comment findById(Long id);
    List<Comment> findAll();
    void create(Comment comment, Long bookId, Long userId);

    void update(Comment comment, Long bookId, Long userId);
}
