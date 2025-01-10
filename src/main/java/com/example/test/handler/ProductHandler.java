package com.example.test.handler;

import com.example.test.dtos.payload.ProductDTO;
import com.example.test.model.Category;
import com.example.test.model.Product;
import com.example.test.service.CategoryService;
import com.example.test.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductHandler {
    private final ProductService productService;
    private final CategoryService categoryService;

    public List<Product> findAll() {
        return productService.getAllProducts();
    }

    public Product findById(Long id) {
        return productService.getById(id);
    }

    public Product createProduct(ProductDTO data) {
        Category existCategory= categoryService.getById(data.getCategory_id());

        Product product = Product.builder()
                .name(data.getName())
                .description(data.getDescription())
                .price(data.getPrice())
                .category(existCategory)
                .build();

        return productService.create(product);
    }

    public Product updateProduct(Long id, ProductDTO data) {
        Product existProduct = productService.getById(id);
        Category existCategory= categoryService.getById(data.getCategory_id());

        existProduct.setName(data.getName());
        existProduct.setDescription(data.getDescription());
        existProduct.setPrice(data.getPrice());
        existProduct.setCategory(existCategory);

        return productService.update(existProduct);
    }

    public Product deleteById(Long id) {
        Product product = productService.getById(id);
        productService.deleteById((product.getId()));

        return product;
    }
}
