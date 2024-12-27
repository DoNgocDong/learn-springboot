package com.example.test.service.impl;

import com.example.test.repository.RequestLogRepository;
import com.example.test.service.RequestLogService;
import org.springframework.stereotype.Service;

@Service
public class RequestLogServiceImpl implements RequestLogService {
    private final RequestLogRepository requestLogRepository;

    public RequestLogServiceImpl(RequestLogRepository requestLogRepository) {
        this.requestLogRepository = requestLogRepository;
    }
}
