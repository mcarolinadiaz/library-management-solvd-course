package com.solvd.library.persistence;

import com.solvd.library.domain.Category;

import java.util.List;

public interface CategoryRepository extends GenericDAO<Category> {
    Category findById(Long id);
    List<Category> findAll();
    void create(Category category);
    void update(Category category);
}
