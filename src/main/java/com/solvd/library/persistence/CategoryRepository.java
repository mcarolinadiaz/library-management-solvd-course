package com.solvd.library.persistence;

import com.solvd.library.domain.Category;

import java.util.List;

public interface CategoryRepository {
    Category findById(int id);
    List<Category> findAll();
    void save(Category category);
    void update(Category category);
    void delete(int id);
}
