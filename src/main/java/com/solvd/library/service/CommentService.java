package com.solvd.library.service;

import com.solvd.library.domain.Comment;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CommentService {
    Optional<Comment> getCommentById(Long id);
    Collection<Comment> getAllComments();
    Comment createComment(Comment comment);
    Comment updateComment(Comment comment);
    void deleteComment(Long id);
}