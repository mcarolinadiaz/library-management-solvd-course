package com.solvd.library.persistence.impl;

import com.solvd.library.domain.Book;
import com.solvd.library.persistence.BookRepository;
import com.solvd.library.persistence.PersistenceConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.Collection;
import java.util.Optional;

public class BookMybatisImpl implements BookRepository {
    @Override
    public void delete(Long id) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            BookRepository bookRepository = sqlSession.getMapper(BookRepository.class);
            bookRepository.delete(id);
        }
    }

    @Override
    public Optional<Book> findById(Long id) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            BookRepository bookRepository = sqlSession.getMapper(BookRepository.class);
            return bookRepository.findById(id);
        }
    }

    @Override
    public Collection<Book> findAll() {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            BookRepository bookRepository = sqlSession.getMapper(BookRepository.class);
            return bookRepository.findAll();
        }
    }

    @Override
    public void create(Book book) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            BookRepository bookRepository = sqlSession.getMapper(BookRepository.class);
            bookRepository.create(book);
        }
    }

    @Override
    public void update(Book book) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            BookRepository bookRepository = sqlSession.getMapper(BookRepository.class);
            bookRepository.update(book);
        }
    }
}
