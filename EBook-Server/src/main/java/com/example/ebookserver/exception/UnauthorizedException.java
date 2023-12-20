package com.example.ebookserver.exception;

/**
 * @author wzb
 */
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}