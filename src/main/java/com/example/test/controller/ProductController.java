package com.example.test.controller;

import com.example.test.dtos.payload.ProductDTO;
import com.example.test.dtos.response.ApiResponseDTO;
import com.example.test.handler.ProductHandler;
import com.example.test.model.Product;
import com.example.test.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name = "ProductController")
@RequiredArgsConstructor
public class ProductController {
    private final ProductHandler productHandler;
    private final ResponseUtil responseUtil;

    @GetMapping({"/", ""})
    public ResponseEntity<ApiResponseDTO<List<Product>>> getProducts() {
        List<Product> data = productHandler.findAll();

        HttpStatus status = HttpStatus.OK;
        String msg = "Get products successfully";

        return responseUtil.buildApiResponse(status, msg, data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Product>> getProductById(@PathVariable(name = "id") Long id) {
        Product product = productHandler.findById(id);

        HttpStatus status = HttpStatus.OK;
        String msg = "Get product successfully";

        return responseUtil.buildApiResponse(status, msg, product);
    }

    @PostMapping({"/", ""})
    public ResponseEntity<ApiResponseDTO<Product>> createProduct(@Valid @RequestBody ProductDTO data) {
        Product product = productHandler.createProduct(data);

        HttpStatus status = HttpStatus.CREATED;
        String msg = "Created product!";

        return responseUtil.buildApiResponse(status, msg, product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Product>> updateProduct(@PathVariable(name = "id") Long id,
                                                                   @Valid @RequestBody ProductDTO data)
    {
        Product product = productHandler.updateProduct(id, data);

        HttpStatus status = HttpStatus.OK;
        String msg = "Updated product!";

        return responseUtil.buildApiResponse(status, msg, product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Product>> deleteProductById(@PathVariable(name = "id") Long id) {
        Product product = productHandler.deleteById(id);

        HttpStatus status = HttpStatus.OK;
        String msg = "Deleted product!";

        return responseUtil.buildApiResponse(status, msg, product);
    }
}
