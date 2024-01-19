package com.solvd.library.persistence.impl;

import com.solvd.library.domain.Comment;
import com.solvd.library.persistence.CommentRepository;
import com.solvd.library.persistence.PersistenceConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.Collection;
import java.util.Optional;

public class CommentMybatisImpl implements CommentRepository {
    @Override
    public void delete(Long id) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            CommentRepository commentRepository = sqlSession.getMapper(CommentRepository.class);
            commentRepository.delete(id);
        }
    }

    @Override
    public Optional<Comment> findById(Long id) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            CommentRepository commentRepository = sqlSession.getMapper(CommentRepository.class);
            return commentRepository.findById(id);
        }
    }

    @Override
    public Collection<Comment> findAll() {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            CommentRepository commentRepository = sqlSession.getMapper(CommentRepository.class);
            return commentRepository.findAll();
        }
    }

    @Override
    public void create(Comment comment) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            CommentRepository commentRepository = sqlSession.getMapper(CommentRepository.class);
            commentRepository.create(comment);
        }
    }

    @Override
    public void update(Comment comment) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            CommentRepository commentRepository = sqlSession.getMapper(CommentRepository.class);
            commentRepository.update(comment);
        }
    }
}
