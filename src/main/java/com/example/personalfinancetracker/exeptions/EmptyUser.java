package com.example.personalfinancetracker.exeptions;

public class EmptyUser extends RuntimeException{
    public EmptyUser(String message){
        super(message);
    }

}
