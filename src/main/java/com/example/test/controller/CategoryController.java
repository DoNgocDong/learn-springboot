package com.example.test.controller;

import com.example.test.dtos.payload.CategoryDTO;
import com.example.test.dtos.response.ApiResponseDTO;
import com.example.test.handler.CategoryHandler;
import com.example.test.messaging.CategoryMsgEmitter;
import com.example.test.model.Category;
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
@RequestMapping("/categories")
@Tag(name = "CategoryController")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryHandler categoryHandler;
    private final ResponseUtil responseUtil;
    private final LocaleUtils localeUtils;

    @GetMapping({"/", ""})
    public ResponseEntity<ApiResponseDTO<List<Category>>> getCategories(WebRequest request) {
        List<Category> data = categoryHandler.findAll();

        HttpStatus status = HttpStatus.OK;
        String msg = localeUtils.getLocaleMsg(MessageKeys.DATA_GET_SUCCESS, request);

        return responseUtil.buildApiResponse(status, msg, data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Category>> getCategoryById(@PathVariable(name = "id") Long id,
                                                                    WebRequest request)
    {
        Category category = categoryHandler.findById(id);

        HttpStatus status = HttpStatus.OK;
        String msg = localeUtils.getLocaleMsg(MessageKeys.DATA_GET_SUCCESS, request);
//        categoryMsgEmitter.send(category);

        return responseUtil.buildApiResponse(status, msg, category);
    }

    @PostMapping({"/", ""})
    public ResponseEntity<ApiResponseDTO<Category>> createCategory(@Valid @RequestBody CategoryDTO data,
                                                                   WebRequest request)
    {
        Category category = categoryHandler.createCategory(data);

        HttpStatus status = HttpStatus.CREATED;
        String msg = localeUtils.getLocaleMsg(MessageKeys.DATA_CREATED, request);

        return responseUtil.buildApiResponse(status, msg, category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Category>> updateCategory(@PathVariable(name = "id") Long id,
                                                                   @Valid @RequestBody CategoryDTO data,
                                                                   WebRequest request)
    {
        Category category = categoryHandler.updateCategory(id, data);

        HttpStatus status = HttpStatus.OK;
        String msg = localeUtils.getLocaleMsg(MessageKeys.DATA_UPDATED, request);

        return responseUtil.buildApiResponse(status, msg, category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Category>> deleteCategoryById(@PathVariable(name = "id") Long id,
                                                                       WebRequest request)
    {
        Category category = categoryHandler.deleteById(id);

        HttpStatus status = HttpStatus.OK;
        String msg = localeUtils.getLocaleMsg(MessageKeys.DATA_DELETED, request);

        return responseUtil.buildApiResponse(status, msg, category);
    }
}
