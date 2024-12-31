package com.example.test.handler;

import com.example.test.exception.ResourceExistedException;
import com.example.test.model.Category;
import com.example.test.repository.CategoryRepository;
import com.example.test.service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryHandler {
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryService.getAllCategories();
    }

    public Category findById(Long id) {
        return categoryService.getById(id);
    }

    public Category createCategory(Category category) {
        boolean exists = categoryRepository.existsByName(category.getName());

        if (exists) {
            throw new ResourceExistedException("category name already exists");
        }

        return categoryService.create(category);
    }

    public Category deleteById(Long id) {
        Category category = categoryService.getById(id);
        categoryService.deleteById((category.getId()));

        return category;
    }
}