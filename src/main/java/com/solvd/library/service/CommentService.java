package com.solvd.library.service;

import com.solvd.library.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Optional<Comment> getCommentById(Long id);
    List<Comment> getAllComments();
    Comment createComment(Comment comment, Long bookId, Long userId);
    Comment updateComment(Comment comment, Long bookId, Long userId);
    void deleteComment(Long id);
}