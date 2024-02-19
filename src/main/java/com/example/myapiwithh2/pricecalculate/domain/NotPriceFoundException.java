package com.example.myapiwithh2.pricecalculate.domain;

public class NotPriceFoundException extends Exception{

    public NotPriceFoundException(String message){
        super(message);
    }
}
