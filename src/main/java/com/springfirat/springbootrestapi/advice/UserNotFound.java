package com.springfirat.springbootrestapi.advice;

//Bu kendi exception sınıfımız
public class UserNotFound extends RuntimeException{

    private static final long serialVersionUID=1L;

    public UserNotFound(String message){
        super(message);
    }
}
