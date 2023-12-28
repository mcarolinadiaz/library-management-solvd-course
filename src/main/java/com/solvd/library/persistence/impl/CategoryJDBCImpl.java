package com.solvd.library.persistence.impl;

import com.solvd.library.domain.Book;
import com.solvd.library.domain.Category;
import com.solvd.library.persistence.CategoryRepository;

import java.sql.Connection;
import java.util.List;

public class CategoryJDBCImpl implements CategoryRepository {

    @Override
    public Category findById(Long id, Connection connection) {
        return null;
    }

    @Override
    public List<Category> findAll(Connection connection) {
        return null;
    }

    @Override
    public void create(Category category, Connection connection) {

    }

    @Override
    public void update(Category category, Connection connection) {

    }

    @Override
    public void delete(Long id, Connection connection) {

    }
}
