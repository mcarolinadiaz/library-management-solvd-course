package com.solvd.library.service;

import com.solvd.library.domain.Comment;

import java.util.List;

public interface CommentService {
    Comment getCommentById(Long id);
    List<Comment> getAllComments();
    Comment createComment(Comment comment, Long bookId, Long userId);
    Comment updateComment(Comment comment, Long bookId, Long userId);
    void deleteComment(Long id);
}