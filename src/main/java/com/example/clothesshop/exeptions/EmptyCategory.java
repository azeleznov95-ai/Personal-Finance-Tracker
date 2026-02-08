package com.example.clothesshop.exeptions;

public class EmptyCategory extends RuntimeException{
    public EmptyCategory(String message){
        super(message);
    }
}
