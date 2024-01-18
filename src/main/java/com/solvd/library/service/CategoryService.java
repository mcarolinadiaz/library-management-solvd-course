package com.solvd.library.service;

import com.solvd.library.domain.Category;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Optional<Category> getCategoryById(Long id);
    Collection<Category> getAllCategorys();
    Category createCategory(Category category);
    Category updateCategory(Category category);
    void deleteCategory(Long id);
}
