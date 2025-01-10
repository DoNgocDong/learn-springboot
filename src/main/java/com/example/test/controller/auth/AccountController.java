package com.example.test.controller.auth;

import com.example.test.dtos.payload.AccountDTO;
import com.example.test.dtos.response.ApiResponseDTO;
import com.example.test.handler.AccountHandler;
import com.example.test.model.Account;
import com.example.test.service.AccountService;
import com.example.test.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/accounts")
@Tag(name = "AccountController")
@RequiredArgsConstructor
public class AccountController {
    private final AccountHandler accountHandler;
    private final ResponseUtil responseUtil;

    @PostMapping({"/", ""})
    public ResponseEntity<ApiResponseDTO<Account>> createAccount(@Valid @RequestBody AccountDTO data) {
        Account acc = accountHandler.createAccount(data);

        HttpStatus status = HttpStatus.CREATED;
        String message = "Account created";

        return responseUtil.buildApiResponse(status, message, acc);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Account>> getAccount(@PathVariable(name = "id") Long id) {
        Account acc = accountHandler.getById(id);

        HttpStatus status = HttpStatus.OK;
        String message = "get Account success";

        return responseUtil.buildApiResponse(status, message, acc);
    }
}
