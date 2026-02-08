package com.example.clothesshop.exeptions;

public class InvalidToken extends RuntimeException{
    public InvalidToken(String message){
        super(message);
    }
}
