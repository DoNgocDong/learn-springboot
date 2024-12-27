package com.example.test.controller.auth;

import com.example.test.model.Account;
import com.example.test.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/accounts", consumes = "application/json")
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
