package com.example.test.service.impl;

import com.example.test.model.Category;
import com.example.test.repository.CategoryRepository;
import com.example.test.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category getById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Category with id " + id + " not found") );
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category create(Category data) {
        Category category = Category.builder()
                .name(data.getName())
                .description(data.getDescription())
                .build();

        return categoryRepository.save(category);
    }

    @Override
    public Boolean deleteById(Long id) {
        try {
            categoryRepository.deleteById(id);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
