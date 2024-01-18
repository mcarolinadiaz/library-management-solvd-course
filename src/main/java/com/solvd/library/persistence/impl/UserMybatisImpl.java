package com.solvd.library.persistence.impl;

import com.solvd.library.domain.User;
import com.solvd.library.persistence.PersistenceConfig;
import com.solvd.library.persistence.UserRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.Collection;
import java.util.Optional;

public class UserMybatisImpl implements UserRepository {
    @Override
    public void delete(Long id) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            UserRepository userRepository = sqlSession.getMapper(UserRepository.class);
            userRepository.delete(id);
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            UserRepository userRepository = sqlSession.getMapper(UserRepository.class);
            return userRepository.findById(id);
        }
    }

    @Override
    public Collection<User> findAll() {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            UserRepository userRepository = sqlSession.getMapper(UserRepository.class);
            return userRepository.findAll();
        }
    }

    @Override
    public void create(User user) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            UserRepository userRepository = sqlSession.getMapper(UserRepository.class);
            userRepository.create(user);
        }
    }

    @Override
    public void update(User user) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            UserRepository userRepository = sqlSession.getMapper(UserRepository.class);
            userRepository.update(user);
        }
    }
}
