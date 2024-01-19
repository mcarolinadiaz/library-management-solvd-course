package com.solvd.library.persistence.impl;

import com.solvd.library.domain.Person;
import com.solvd.library.persistence.PersistenceConfig;
import com.solvd.library.persistence.PersonRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class PersonMybatisImpl implements PersonRepository {
    @Override
    public void delete(Long id) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            PersonRepository personRepository = sqlSession.getMapper(PersonRepository.class);
            personRepository.delete(id);
        }
    }

    @Override
    public Optional<Person> findById(Long id) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            PersonRepository personRepository = sqlSession.getMapper(PersonRepository.class);
            return personRepository.findById(id);
        }
    }

    @Override
    public Collection<Person> findAll() {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            PersonRepository personRepository = sqlSession.getMapper(PersonRepository.class);
            return personRepository.findAll();
        }
    }

    @Override
    public void create(Person person) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            PersonRepository personRepository = sqlSession.getMapper(PersonRepository.class);
            personRepository.create(person);
        }
    }

    @Override
    public void update(Person person) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            PersonRepository personRepository = sqlSession.getMapper(PersonRepository.class);
            personRepository.update(person);
        }
    }
}
