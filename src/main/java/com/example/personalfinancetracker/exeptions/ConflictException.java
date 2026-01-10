package com.example.personalfinancetracker.exeptions;

public class ConflictException extends Exception {
    public ConflictException(String message){
        super(message);
    }
}
