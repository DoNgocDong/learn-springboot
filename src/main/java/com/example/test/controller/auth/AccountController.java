package com.example.test.controller.auth;

import com.example.test.model.Account;
import com.example.test.service.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/accounts")
@Tag(name = "AccountController")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/")
    public ResponseEntity<Account> createAccount(@Valid @RequestBody Account data) {
        Account acc = accountService.createAccount(data);

        return ResponseEntity.ok(acc);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable(name = "id") Long id) {
        Account data = accountService.getById(id);

        return ResponseEntity.ok(data);
    }
}
