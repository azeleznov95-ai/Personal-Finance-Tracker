package com.example.clothesshop.exeptions;

public class IdIsInvalid extends RuntimeException{
    public IdIsInvalid(String message){
        super(message);
    }
}
