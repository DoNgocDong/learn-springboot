package com.example.test.service;

import com.example.test.model.AccountEntity;
import com.example.test.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountEntity createAccount(AccountEntity account) {
        return accountRepository.save(account);
    }
}
