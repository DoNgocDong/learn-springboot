package com.example.test.handler;

import com.example.test.dtos.payload.AccountDTO;
import com.example.test.exception.ResourceExistedException;
import com.example.test.model.Account;
import com.example.test.service.AccountService;
import com.example.test.utils.Encoder;
import com.example.test.utils.MessageKeys;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountHandler {
    private final AccountService accountService;
    private final Encoder encoder;

    public Account createAccount(AccountDTO data) {
        boolean exists = accountService.existedByEmail(data.getEmail());

        if (exists) {
            throw new ResourceExistedException(MessageKeys.ACCOUNT_EXISTED, data.getEmail());
        }

        Account account = Account.builder()
                .email(data.getEmail())
                .password(encoder.encodePassword(data.getPassword()))
                .role(data.getRole())
                .name(data.getName())
                .build();

        return accountService.createAccount(account);
    }

    public Account getById(Long id) {
        return accountService.getById(id);
    }
}
