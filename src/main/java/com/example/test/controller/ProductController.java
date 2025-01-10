package com.example.test.controller;

import com.example.test.dtos.payload.ProductDTO;
import com.example.test.dtos.response.ApiResponseDTO;
import com.example.test.handler.ProductHandler;
import com.example.test.model.Product;
import com.example.test.utils.LocaleUtils;
import com.example.test.utils.MessageKeys;
import com.example.test.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name = "ProductController")
@RequiredArgsConstructor
public class ProductController {
    private final ProductHandler productHandler;
    private final ResponseUtil responseUtil;
    private final LocaleUtils localeUtils;

    @GetMapping({"/", ""})
    public ResponseEntity<ApiResponseDTO<List<Product>>> getProducts(WebRequest request) {
        List<Product> data = productHandler.findAll();

        HttpStatus status = HttpStatus.OK;
        String msg = localeUtils.getLocaleMsg(MessageKeys.DATA_GET_SUCCESS, request);

        return responseUtil.buildApiResponse(status, msg, data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Product>> getProductById(@PathVariable(name = "id") Long id,
                                                                  WebRequest request)
    {
        Product product = productHandler.findById(id);

        HttpStatus status = HttpStatus.OK;
        String msg = localeUtils.getLocaleMsg(MessageKeys.DATA_GET_SUCCESS, request);

        return responseUtil.buildApiResponse(status, msg, product);
    }

    @PostMapping({"/", ""})
    public ResponseEntity<ApiResponseDTO<Product>> createProduct(@Valid @RequestBody ProductDTO data,
                                                                 WebRequest request)
    {
        Product product = productHandler.createProduct(data);

        HttpStatus status = HttpStatus.CREATED;
        String msg = localeUtils.getLocaleMsg(MessageKeys.DATA_CREATED, request);

        return responseUtil.buildApiResponse(status, msg, product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Product>> updateProduct(@PathVariable(name = "id") Long id,
                                                                 @Valid @RequestBody ProductDTO data,
                                                                 WebRequest request)
    {
        Product product = productHandler.updateProduct(id, data);

        HttpStatus status = HttpStatus.OK;
        String msg = localeUtils.getLocaleMsg(MessageKeys.DATA_UPDATED, request);

        return responseUtil.buildApiResponse(status, msg, product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Product>> deleteProductById(@PathVariable(name = "id") Long id,
                                                                     WebRequest request)
    {
        Product product = productHandler.deleteById(id);

        HttpStatus status = HttpStatus.OK;
        String msg = localeUtils.getLocaleMsg(MessageKeys.DATA_DELETED, request);

        return responseUtil.buildApiResponse(status, msg, product);
    }
}
