package com.example.test.service;

import com.example.test.model.Product;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.List;

public interface ProductService {
    Product getById(Long id) throws ResourceNotFoundException;

    List<Product> getAllProducts();

    Product create(Product data);

    Product update(Product data);

    void deleteById(Long id);
}
