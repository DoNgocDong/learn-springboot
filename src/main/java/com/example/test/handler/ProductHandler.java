package com.example.test.handler;

import com.example.test.exception.ResourceExistedException;
import com.example.test.model.Product;
import com.example.test.repository.ProductRepository;
import com.example.test.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductHandler {
    private final ProductRepository productRepository;
    private final ProductService productService;

    public List<Product> findAll() {
        return productService.getAllProducts();
    }

    public Product findById(Long id) {
        return productService.getById(id);
    }

    public Product createProduct(Product product) {
        boolean exists = productRepository.existsByName(product.getName());

        if (exists) {
            throw new ResourceExistedException("Product name already exists");
        }

        return productService.create(product);
    }

    public Product updateProduct(Long id, Product data) {
        Product product = productService.getById(id);

        product.setName(data.getName());
        product.setDescription(data.getDescription());

        return productService.update(product);
    }

    public Product deleteById(Long id) {
        Product product = productService.getById(id);
        productService.deleteById((product.getId()));

        return product;
    }
}
