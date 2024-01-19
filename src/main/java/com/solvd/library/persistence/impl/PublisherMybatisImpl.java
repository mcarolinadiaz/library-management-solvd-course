package com.solvd.library.persistence.impl;

import com.solvd.library.domain.Publisher;
import com.solvd.library.persistence.PersistenceConfig;
import com.solvd.library.persistence.PublisherRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.Collection;
import java.util.Optional;

public class PublisherMybatisImpl implements PublisherRepository {
    @Override
    public void delete(Long id) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            PublisherRepository publisherRepository = sqlSession.getMapper(PublisherRepository.class);
            publisherRepository.delete(id);
        }
    }

    @Override
    public Optional<Publisher> findById(Long id) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            PublisherRepository publisherRepository = sqlSession.getMapper(PublisherRepository.class);
            return publisherRepository.findById(id);
        }
    }

    @Override
    public Collection<Publisher> findAll() {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            PublisherRepository publisherRepository = sqlSession.getMapper(PublisherRepository.class);
            return publisherRepository.findAll();
        }
    }

    @Override
    public void create(Publisher publisher) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            PublisherRepository publisherRepository = sqlSession.getMapper(PublisherRepository.class);
            publisherRepository.create(publisher);
        }
    }

    @Override
    public void update(Publisher publisher) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            PublisherRepository publisherRepository = sqlSession.getMapper(PublisherRepository.class);
            publisherRepository.update(publisher);
        }
    }
}
