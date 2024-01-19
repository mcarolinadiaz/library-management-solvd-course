package com.solvd.library.service.impl;


import com.solvd.library.domain.Comment;
import com.solvd.library.persistence.CommentRepository;
import com.solvd.library.persistence.impl.CommentJDBCImpl;
import com.solvd.library.persistence.impl.CommentMybatisImpl;
import com.solvd.library.service.CommentService;

import java.util.Collection;
import java.util.Optional;

public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    public CommentServiceImpl() {
        //this.commentRepository = new CommentJDBCImpl();
        this.commentRepository = new CommentMybatisImpl();
    }

    @Override
    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public Collection<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Comment createComment(Comment comment) {
        comment.setId(null);
        commentRepository.create(comment);
        return comment;
    }

    @Override
    public Comment updateComment(Comment comment) {
        commentRepository.update(comment);
        return comment;
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.delete(id);
    }
}
