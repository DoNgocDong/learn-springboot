package com.example.test.service;

import com.example.test.model.Account;
import com.example.test.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AccountService {
    Account createAccount(Account account);

    List<Account> getAll();

    Account getById(Long id);

    boolean existedByEmail(String email);
}
