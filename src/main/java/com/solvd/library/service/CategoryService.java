package com.solvd.library.service;

import com.solvd.library.domain.Category;

import java.util.List;

public interface CategoryService {
    Category getCategoryById(Long id);
    List<Category> getAllCategorys();
    Category createCategory(Category category);
    Category updateCategory(Category category);
    void deleteCategory(Long id);
}
