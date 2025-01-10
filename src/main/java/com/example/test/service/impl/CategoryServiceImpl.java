package com.example.test.service.impl;

import com.example.test.model.Category;
import com.example.test.repository.CategoryRepository;
import com.example.test.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    @Cacheable(value = "categories", key = "#id")
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
        return categoryRepository.save(data);
    }

    @Override
    @CachePut(value = "categories", key = "#updateData.id")
    public Category update(Category updateData) {
        return categoryRepository.save(updateData);
    }

    @Override
    @CacheEvict(value = "categories", key = "#id")
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }
}
