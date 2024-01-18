package com.solvd.library.persistence.impl;

import com.solvd.library.domain.Category;
import com.solvd.library.persistence.CategoryRepository;
import com.solvd.library.persistence.PersistenceConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.Collection;
import java.util.Optional;

public class CategoryMybatisImpl implements CategoryRepository {

    @Override
    public void delete(Long id) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            CategoryRepository categoryRepository = sqlSession.getMapper(CategoryRepository.class);
            categoryRepository.delete(id);
        }
    }

    @Override
    public Optional<Category> findById(Long id) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            CategoryRepository categoryRepository = sqlSession.getMapper(CategoryRepository.class);
            return categoryRepository.findById(id);
        }
    }

    @Override
    public Collection<Category> findAll() {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            CategoryRepository categoryRepository = sqlSession.getMapper(CategoryRepository.class);
            return categoryRepository.findAll();
        }
    }

    @Override
    public void create(Category category) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            CategoryRepository categoryRepository = sqlSession.getMapper(CategoryRepository.class);
            categoryRepository.create(category);
        }
    }

    @Override
    public void update(Category category) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            CategoryRepository categoryRepository = sqlSession.getMapper(CategoryRepository.class);
            categoryRepository.update(category);
        }
    }
}
