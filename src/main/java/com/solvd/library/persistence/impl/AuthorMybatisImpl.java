package com.solvd.library.persistence.impl;

import com.solvd.library.domain.Author;
import com.solvd.library.persistence.AuthorRepository;
import com.solvd.library.persistence.PersistenceConfig;
import com.solvd.library.persistence.AuthorRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.Collection;
import java.util.Optional;

public class AuthorMybatisImpl implements AuthorRepository {
    @Override
    public Optional<Author> findById(Long id) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            AuthorRepository authorRepository = sqlSession.getMapper(AuthorRepository.class);
            return authorRepository.findById(id);
        }
    }
        
    @Override
    public Collection<Author> findAll() {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            AuthorRepository authorRepository = sqlSession.getMapper(AuthorRepository.class);
            return authorRepository.findAll();
        }
    }

    @Override
    public void delete(Long id) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            AuthorRepository authorRepository = sqlSession.getMapper(AuthorRepository.class);
            authorRepository.delete(id);
        }
    }

    @Override
    public void create(Author author) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            AuthorRepository authorRepository = sqlSession.getMapper(AuthorRepository.class);
            authorRepository.create(author);
        }
    }

    @Override
    public void update(Author author) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            AuthorRepository authorRepository = sqlSession.getMapper(AuthorRepository.class);
            authorRepository.update(author);
        }
    }
}
