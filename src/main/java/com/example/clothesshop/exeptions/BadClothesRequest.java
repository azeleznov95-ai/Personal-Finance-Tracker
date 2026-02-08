package com.example.clothesshop.exeptions;

public class BadClothesRequest extends RuntimeException {
    public BadClothesRequest(String message) {
        super(message);
    }
}
