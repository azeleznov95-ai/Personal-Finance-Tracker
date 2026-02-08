package com.example.clothesshop.exeptions;

public class IncorrectLoginOrPasswordException extends RuntimeException{
    public IncorrectLoginOrPasswordException(String message){
        super(message);
    }
}
