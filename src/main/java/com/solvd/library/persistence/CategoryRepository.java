package com.solvd.library.persistence;

import com.solvd.library.domain.Book;
import com.solvd.library.domain.Category;
import com.solvd.library.domain.Employee;

import java.sql.Connection;
import java.util.List;

public interface CategoryRepository extends GenericDAO<Category> {
    Category findById(Long id, Connection connection);
    List<Category> findAll(Connection connection);
    void create(Category category, Connection connection);
    void update(Category category, Connection connection);
}
