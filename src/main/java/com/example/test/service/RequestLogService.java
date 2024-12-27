package com.example.test.service;

import com.example.test.repository.RequestLogRepository;
import org.springframework.stereotype.Service;

@Service
public class RequestLogService {
    private final RequestLogRepository requestLogRepository;

    public RequestLogService(RequestLogRepository requestLogRepository) {
        this.requestLogRepository = requestLogRepository;
    }
}
