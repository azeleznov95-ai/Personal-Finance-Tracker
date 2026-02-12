package com.example.clothesshop.exeptions;

public class BadCartRequestException extends RuntimeException {
    public BadCartRequestException(String message) {
        super(message);
    }
}
