package com.example.clothesshop.exeptions;

public class EmptyUser extends RuntimeException{
    public EmptyUser(String message){
        super(message);
    }

}
