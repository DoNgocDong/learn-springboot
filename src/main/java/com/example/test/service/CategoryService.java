package com.example.test.service;

import com.example.test.model.Category;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.List;

public interface CategoryService {
    Category getById(Long id) throws ResourceNotFoundException;

    List<Category> getAllCategories();

    Category create(Category data);

    void deleteById(Long id);
}
