package com.solvd.library.service.impl;


import com.solvd.library.domain.Category;
import com.solvd.library.persistence.CategoryRepository;
import com.solvd.library.persistence.impl.CategoryJDBCImpl;
import com.solvd.library.service.BookService;
import com.solvd.library.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final BookService bookService = new BookServiceImpl();

    public CategoryServiceImpl() {
        this.categoryRepository = new CategoryJDBCImpl();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> getAllCategorys() {
        return categoryRepository.findAll();
    }

    @Override
    public Category createCategory(Category category) {
        category.setId(null);
        categoryRepository.create(category);
        return category;
    }

    @Override
    public Category updateCategory(Category category) {
        categoryRepository.update(category);
        return category;
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.delete(id);
    }
}