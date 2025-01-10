package com.example.test.handler;

import com.example.test.dtos.payload.CategoryDTO;
import com.example.test.exception.ResourceExistedException;
import com.example.test.model.Category;
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

    public List<Category> findAll() {
        return categoryService.getAllCategories();
    }

    public Category findById(Long id) {
        return categoryService.getById(id);
    }

    public Category createCategory(CategoryDTO data) {
        boolean exists = categoryService.existsByName(data.getName());

        if (exists) {
            throw new ResourceExistedException("Category name already exists");
        }

        Category category = Category.builder()
                .name(data.getName())
                .description(data.getDescription())
                .build();

        return categoryService.create(category);
    }

    public Category updateCategory(Long id, CategoryDTO data) {
        Category category = categoryService.getById(id);

        category.setName(data.getName());
        category.setDescription(data.getDescription());

        return categoryService.update(category);
    }

    public Category deleteById(Long id) {
        Category category = categoryService.getById(id);
        categoryService.deleteById((category.getId()));

        return category;
    }
}
