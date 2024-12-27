package com.example.test.controller.logs;

import com.example.test.service.RequestLogService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/logs")
public class RequestLogController {
    private final RequestLogService requestLogService;

    public RequestLogController(RequestLogService requestLogService) {
        this.requestLogService = requestLogService;
    }
}
