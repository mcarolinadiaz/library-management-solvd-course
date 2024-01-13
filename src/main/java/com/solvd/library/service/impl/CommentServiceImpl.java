package com.solvd.library.service.impl;


import com.solvd.library.domain.Comment;
import com.solvd.library.persistence.CommentRepository;
import com.solvd.library.persistence.impl.CommentJDBCImpl;
import com.solvd.library.service.CommentService;

import java.util.List;

public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    public CommentServiceImpl() {
        this.commentRepository = new CommentJDBCImpl();
    }

    @Override
    public Comment getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Comment createComment(Comment comment, Long bookId, Long userId) {
        comment.setId(null);
        commentRepository.create(comment, bookId, userId);
        return comment;
    }

    @Override
    public Comment updateComment(Comment comment, Long bookId, Long userId) {
        commentRepository.update(comment, bookId, userId);
        return comment;
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.delete(id);
    }
}
