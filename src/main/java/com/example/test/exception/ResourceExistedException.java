package com.example.test.exception;

public class ResourceExistedException extends RuntimeException {
    public ResourceExistedException(final String message) {
        super(message);
    }
}
