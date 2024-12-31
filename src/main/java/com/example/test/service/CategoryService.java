package com.example.test.service;

import com.example.test.model.Category;

import java.util.List;

public interface CategoryService {
    Category getById(Long id);

    List<Category> getAllCategories();

    Category create(Category data);

    Boolean deleteById(Long id);
}
