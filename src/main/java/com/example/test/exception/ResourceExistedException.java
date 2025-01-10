package com.example.test.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Arrays;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ResourceExistedException extends RuntimeException {
    private String msgKey;
    private List<Object> args;

    public ResourceExistedException(String msgKey, Object ...args) {
        this.msgKey = msgKey;
        this.args = Arrays.asList(args);
    }
}
