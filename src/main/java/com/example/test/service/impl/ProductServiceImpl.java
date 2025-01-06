package com.example.test.service.impl;

import com.example.test.model.Product;
import com.example.test.repository.ProductRepository;
import com.example.test.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    @Cacheable(value = "products", key = "#id")
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Product with id " + id + " not found") );
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product create(Product data) {
        return productRepository.save(data);
    }

    @Override
    @CachePut(value = "products", key = "#updateData.id")
    public Product update(Product updateData) {
        return productRepository.save(updateData);
    }

    @Override
    @CacheEvict(value = "products", key = "#id")
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
